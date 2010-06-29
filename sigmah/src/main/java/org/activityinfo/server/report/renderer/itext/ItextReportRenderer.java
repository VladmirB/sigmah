package org.activityinfo.server.report.renderer.itext;

import com.google.inject.Inject;
import com.lowagie.text.DocWriter;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import org.activityinfo.server.report.renderer.Renderer;
import org.activityinfo.shared.report.model.*;

import java.io.IOException;
import java.io.OutputStream;


/**
 * Base class for iText-based {@link org.activityinfo.shared.report.model.Report} renderers.
 * Subclasses ({@link org.activityinfo.server.report.renderer.itext.PdfReportRenderer PdfReportRenderer},
 * {@link org.activityinfo.server.report.renderer.itext.RtfReportRenderer RtfReportRenderer} target
 * specific output formats.
 *
 * @author Alex Bertram
 */
public abstract class ItextReportRenderer implements Renderer {

    private final ItextPivotTableRenderer pivotTableRenderer;
    private final ItextChartRenderer chartRenderer;
    private final ItextMapRenderer mapRenderer;
    private final ItextTableRenderer tableRenderer;

    @Inject
    public ItextReportRenderer(ItextPivotTableRenderer pivotTableRenderer, ItextChartRenderer chartRenderer, ItextMapRenderer mapRenderer, ItextTableRenderer tableRenderer) {
        this.pivotTableRenderer = pivotTableRenderer;
        this.chartRenderer = chartRenderer;
        this.mapRenderer = mapRenderer;
        this.tableRenderer = tableRenderer;
    }

    public void render(ReportElement element, OutputStream os) throws IOException {

        try {
            Document document = new Document();
            DocWriter writer = createWriter(document, os);
            document.open();

            if(element instanceof Report) {
                renderReport(element, document, writer);
            } else {
                renderElement(writer, element, document);
            }

            document.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Provides a DocWriter for an open document and OutputStream. Subclasses should provide
     * an implementation for their specific output format.
     *
     * @param document
     * @param os
     * @return
     * @throws DocumentException
     */
    protected abstract DocWriter createWriter(Document document, OutputStream os) throws DocumentException;


    private void renderReport(ReportElement element, Document document, DocWriter writer) throws DocumentException {
        Report report = (Report) element;
        document.add(ThemeHelper.reportTitle(report.getTitle()));
        ItextRendererHelper.addFilterDescription(document, report.getContent().getFilterDescriptions());

        for(ReportElement childElement : report.getElements()) {
            renderElement(writer, childElement, document);
        }
    }

    private void renderElement(DocWriter writer, ReportElement element, Document document) {
        try {
            rendererForElement(element).render(writer, document, element);
        } catch(DocumentException e) {
            throw new RuntimeException(e);
        }
    }

    private ItextRenderer rendererForElement(ReportElement element) {
        if(element instanceof PivotTableElement) {
            return pivotTableRenderer;
        } else if(element instanceof PivotChartElement) {
            return chartRenderer;
        } else if(element instanceof MapElement) {
            return mapRenderer;
        } else if(element instanceof TableElement) {
            return tableRenderer;
        } else {
            return new NullItextRenderer();
        }
    }

    private static class NullItextRenderer implements ItextRenderer {
        @Override
        public void render(DocWriter writer, Document doc, ReportElement element) throws DocumentException {

        }
    }
}