package org.nanotek.base.bean.projections;

import org.nanotek.Base;
import org.nanotek.repository.jpa.projections.Projection;
import org.springframework.beans.factory.annotation.Value;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@Projection		
public interface ArtistVirtualProjection extends Base{

		@JsonInclude(Include.NON_NULL)
		@JsonProperty(value = "gid")
		@Value("#{target.gid}")
		public String getGid();
		
		@JsonInclude(Include.NON_NULL)
		@JsonProperty(value = "name")
		@Value("#{target.name}")
		public String getName();
		
		@JsonInclude(Include.NON_NULL)
		@JsonProperty(value = "gender")
		@Value("#{target.gender !=null ? target.gender.name : null}")
		public String getGenderName();
		
		@JsonInclude(Include.NON_NULL)
		@JsonProperty(value = "area")
		@Value("#{target.area != null ? target.area.name : null}")
		public String getArea();
		
		@JsonInclude(Include.NON_NULL)
		@JsonProperty(value = "beginDate")
		@Value("#{target.artistBeginDate != null? T(org.nanotek.MaskBase).DATEMASK(target.artistBeginDate.year,target.artistBeginDate.month,target.artistBeginDate.day) : null}")
		public String getBeginDate();
		
		@JsonInclude(Include.NON_NULL)
		@JsonProperty(value = "endDate")
		@Value("#{target.artistEndDate != null? T(org.nanotek.MaskBase).DATEMASK(target.artistEndDate.year,target.artistEndDate.month,target.artistEndDate.day) : null}" )
		public String getEndDate();
	
}
