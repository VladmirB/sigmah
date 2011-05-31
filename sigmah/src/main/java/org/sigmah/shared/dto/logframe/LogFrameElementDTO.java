package org.sigmah.shared.dto.logframe;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.sigmah.client.page.project.logframe.grid.Row.Positionable;
import org.sigmah.shared.dto.EntityDTO;
import org.sigmah.shared.dto.IndicatorDTO;

import com.extjs.gxt.ui.client.data.BaseModelData;

public abstract class LogFrameElementDTO extends BaseModelData implements EntityDTO, Positionable  {

	private List<IndicatorDTO> indicators;
	
	public LogFrameElementDTO() {
		indicators = new ArrayList<IndicatorDTO>();
	}
	

    // Objective id.
    @Override
    public int getId() {
        final Integer id = (Integer) get("id");
        return id != null ? id : -1;
    }

    public void setId(int id) {
        set("id", id);
    }

    // Objective code.
    public Integer getCode() {
        return get("code");
    }

    public void setCode(Integer code) {
        set("code", code);
    }

    // Objective position in its group.
    public Integer getPosition() {
        return get("position");
    }

    @Override
    public void setPosition(Integer position) {
        set("position", position);
    }


    // Result risks.
    public String getRisks() {
        return get("risks");
    }

    public void setRisks(String risks) {
        set("risks", risks);
    }

    // Result assumptions.
    public String getAssumptions() {
        return get("assumptions");
    }

    public void setAssumptions(String assumptions) {
        set("assumptions", assumptions);
    }
    

    // Objective group.
    public LogFrameGroupDTO getGroup() {
        return get("group");
    }

    public void setGroup(LogFrameGroupDTO logFrameGroupDTO) {
        set("group", logFrameGroupDTO);
    }

    public List<IndicatorDTO> getIndicators() {
		return indicators;
	}
    
	public void setIndicators(List<IndicatorDTO> indicators) {
		this.indicators = indicators;
	}


	/**
     * Gets the client-side id for this entity. If this entity has a server-id
     * id, it's returned. Otherwise, a temporary id is generated and returned.
     * 
     * @return The client-side id.
     */
    public int getClientSideId() {

        // Server-side id.
        Integer id = (Integer) get("id");

        if (id == null) {

            // Client-side id.
            id = (Integer) get("tmpid");

            // Generates the client-side id once.
            if (id == null) {
                id = generateClientSideId();
            }
        }

        return id;
    }
    
    /**
     * Generate a client-side unique id for this entity and stores it in the
     * <code>temporaryId</code> attribute.
     */
    private int generateClientSideId() {
        final int id = (int) new Date().getTime();
        set("tmpid", id);
        return id;
    } 
}