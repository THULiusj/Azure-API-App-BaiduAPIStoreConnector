package com.example.baiduapistoreconnector.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "Detailed information about a city, including province and area ID.")
public class CityDetailInfo {

    @JsonProperty("province_cn")
    @Schema(description = "Province name in Chinese.", example = "北京")
    private String provinceCn; // 省份

    @JsonProperty("district_cn")
    @Schema(description = "District name in Chinese.", example = "北京")
    private String districtCn; // 市

    @JsonProperty("name_cn")
    @Schema(description = "City/County name in Chinese.", example = "北京")
    private String nameCn;     // 县

    @JsonProperty("name_en")
    @Schema(description = "City/County name in English.", example = "beijing")
    private String nameEn;     // 县英文

    @JsonProperty("area_id")
    @Schema(description = "Area ID for the city.", example = "101010100")
    private String areaId;     // 区域ID

    // Getters and setters
    public String getProvinceCn() {
        return provinceCn;
    }

    public void setProvinceCn(String provinceCn) {
        this.provinceCn = provinceCn;
    }

    public String getDistrictCn() {
        return districtCn;
    }

    public void setDistrictCn(String districtCn) {
        this.districtCn = districtCn;
    }

    public String getNameCn() {
        return nameCn;
    }

    public void setNameCn(String nameCn) {
        this.nameCn = nameCn;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }
}
