/*
 * All Sigmah code is released under the GNU General Public License v3
 * See COPYRIGHT.txt and LICENSE.txt.
 */

package org.sigmah.server.domain;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * 
 * @author Alex Bertram
 *
 */
@Entity
@org.hibernate.annotations.Filter(
		name="hideDeleted",
		condition="DateDeleted is null"
	)
public class Indicator implements java.io.Serializable, Orderable, Deleteable, SchemaElement {

	private int id;
    private Date dateDeleted;

	private String name;
	private String units;
	private String description;
	
	private String sector;
	private String category;
	
	private boolean collectIntervention;
	private boolean collectMonitoring;
	
	private Activity activity;
	
	private int aggregation;
	
	private int sortOrder;
	private String listHeader;
	
	private QualityCriterion qualityCriterion;

	//private Set<IndicatorValue> indicatorValues = new HashSet<IndicatorValue>(0);

	public Indicator() {
		
	}
	

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "IndicatorId", unique = true, nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int indicatorId) {
		this.id = indicatorId;
	}

    @Column(name = "Name", nullable = false, length = 128)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "Sector", nullable = true, length = 50)
	public String getSector() {
		return this.sector;
	}
	
	public void setSector(String sector) { 
		this.sector = sector;
	}
	
	@Column(name = "Units", nullable = false, length = 15)
	public String getUnits() {
		return this.units;
	}

	public void setUnits(String units) {
		this.units = units;
	}

    @Lob
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ActivityId", nullable = false)
	public Activity getActivity() {
		return this.activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	@Column(name = "CollectIntervention", nullable = false)
	public boolean getCollectIntervention() {
		return this.collectIntervention;
	}

	public void setCollectIntervention(boolean collectIntervention) {
		this.collectIntervention = collectIntervention;
	}

	@Column(name = "Aggregation", nullable = false)
	public int getAggregation() {
		return this.aggregation;
	}

	public void setAggregation(int aggregation) {
		this.aggregation = aggregation;
	}

	@Column(name = "CollectMonitoring", nullable = false)
	public boolean isCollectMonitoring() {
		return this.collectMonitoring;
	}

	public void setCollectMonitoring(boolean collectMonitoring) {
		this.collectMonitoring = collectMonitoring;
	}

	@Column(name = "SortOrder", nullable = false)
	public int getSortOrder() {
		return this.sortOrder;
	}

	public void setSortOrder(int sortOrder) {
		this.sortOrder = sortOrder;
	}

	@Column(name = "ListHeader", length = 30)
	public String getListHeader() {
		return this.listHeader;
	}

	public void setListHeader(String listHeader) {
		this.listHeader = listHeader;
	}

	@Column(name = "Category", length = 50)
	public String getCategory() {
		return this.category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Column
	@Temporal(value = TemporalType.TIMESTAMP)
	public Date getDateDeleted() {
		return this.dateDeleted;
	}
	
	public void setDateDeleted(Date deleteTime) {
		this.dateDeleted = deleteTime;
	}
	
	public void delete() {
		setDateDeleted(new Date());
	}


	@Override
	@Transient
	public boolean isDeleted() {
		return getDateDeleted() == null;
	}

	@ManyToOne(optional = true)
	@JoinColumn(name = "id_quality_criterion", nullable = true)
	public QualityCriterion getQualityCriterion() {
		return qualityCriterion;
	}
	
	public void setQualityCriterion(QualityCriterion qualityCriterion) {
		this.qualityCriterion = qualityCriterion;
	}
}