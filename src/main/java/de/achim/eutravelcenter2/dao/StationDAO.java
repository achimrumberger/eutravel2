package de.achim.eutravelcenter2.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "stations")
public class StationDAO {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "station_id")
	private Integer id;
	@Column(name = "station_name", length=50, columnDefinition = "TEXT")
	private String name;
	@Column(length=50, columnDefinition = "TEXT")
	private String slug;
	@Column(length=50, columnDefinition = "TEXT")
	private String uic;
	@Column(length=50, columnDefinition = "TEXT")
	private String uic8_sncf;
	@Column(length=50, columnDefinition = "TEXT")
	private String latitude;
	@Column(length=50, columnDefinition = "TEXT")
	private String longitude;
	@Column(length=50, columnDefinition = "TEXT")
	private String parent_station_id;
	@Column(length=50, columnDefinition = "TEXT")
	private String country;
	@Column(length=50, columnDefinition = "TEXT")
	private String country_time_zone;
	@Column(length=50, columnDefinition = "TEXT")
	private String is_city;
	@Column(length=50, columnDefinition = "TEXT")
	private String is_main_station;
	@Column(length=50, columnDefinition = "TEXT")
	private String is_airport;
	@Column(length=50, columnDefinition = "TEXT")
	private String is_suggestable;
	@Column(length=50, columnDefinition = "TEXT")
	private String country_hint;
	@Column(length=50, columnDefinition = "TEXT")
	private String main_station_hint;
	@Column(length=50, columnDefinition = "TEXT")
	private String sncf_id;
	@Column(length=50, columnDefinition = "TEXT")
	private String sncf_tvs_id;
	@Column(length=50, columnDefinition = "TEXT")
	private String sncf_is_enabled;
	@Column(length=50, columnDefinition = "TEXT")
	private String entur_id;
	@Column(length=50, columnDefinition = "TEXT")
	private String entur_is_enabled;
	@Column(length=50, columnDefinition = "TEXT")
	private String db_id;
	@Column(length=50, columnDefinition = "TEXT")
	private String db_is_enabled;
	@Column(length=50, columnDefinition = "TEXT")
	private String busbud_id;
	@Column(length=50, columnDefinition = "TEXT")
	private String busbud_is_enabled;
	@Column(length=50, columnDefinition = "TEXT")
	private String distribusion_id;
	@Column(length=50, columnDefinition = "TEXT")
	private String distribusion_is_enabled;
	@Column(length=50, columnDefinition = "TEXT")
	private String flixbus_id;
	@Column(length=50, columnDefinition = "TEXT")
	private String flixbus_is_enabled;
	@Column(length=50, columnDefinition = "TEXT")
	private String cff_id;
	@Column(length=50, columnDefinition = "TEXT")
	private String cff_is_enabled;
	@Column(length=50, columnDefinition = "TEXT")
	private String leoexpress_id;
	@Column(length=50, columnDefinition = "TEXT")
	private String leoexpress_is_enabled;
	@Column(length=50, columnDefinition = "TEXT")
	private String obb_id;
	@Column(length=50, columnDefinition = "TEXT")
	private String obb_is_enabled;
	@Column(length=50, columnDefinition = "TEXT")
	private String ouigo_id;
	@Column(length=50, columnDefinition = "TEXT")
	private String ouigo_is_enabled;
	@Column(length=50, columnDefinition = "TEXT")
	private String trenitalia_id;
	@Column(length=50, columnDefinition = "TEXT")
	private String trenitalia_is_enabled;
	@Column(length=50, columnDefinition = "TEXT")
	private String trenitalia_rtvt_id;
	@Column(length=50, columnDefinition = "TEXT")
	private String ntv_rtiv_id;
	@Column(length=50, columnDefinition = "TEXT")
	private String ntv_id;
	@Column(length=50, columnDefinition = "TEXT")
	private String ntv_is_enabled;
	@Column(length=50, columnDefinition = "TEXT")
	private String hkx_id;
	@Column(length=50, columnDefinition = "TEXT")
	private String hkx_is_enabled;
	@Column(length=50, columnDefinition = "TEXT")
	private String renfe_id;
	@Column(length=50, columnDefinition = "TEXT")
	private String renfe_is_enabled;
	@Column(length=50, columnDefinition = "TEXT")
	private String atoc_id;
	@Column(length=50, columnDefinition = "TEXT")
	private String atoc_is_enabled;
	@Column(length=50, columnDefinition = "TEXT")
	private String benerail_id;
	@Column(length=50, columnDefinition = "TEXT")
	private String benerail_is_enabled;
	@Column(length=50, columnDefinition = "TEXT")
	private String westbahn_id;
	@Column(length=50, columnDefinition = "TEXT")
	private String westbahn_is_enabled;
	@Column(length=50, columnDefinition = "TEXT")
	private String sncf_self_service_machine;
	@Column(length=50, columnDefinition = "TEXT")
	private String same_as;
	@Column(length=50, columnDefinition = "TEXT")
	private String info_de;
	@Column(length=50, columnDefinition = "TEXT")
	private String info_en;
	@Column(length=50, columnDefinition = "TEXT")
	private String info_es;
	@Column(length=50, columnDefinition = "TEXT")
	private String info_fr;
	@Column(length=50, columnDefinition = "TEXT")
	private String info_it;
	@Column(length=50, columnDefinition = "TEXT")
	private String info_nb;
	@Column(length=50, columnDefinition = "TEXT")
	private String info_nl;
	@Column(length=50, columnDefinition = "TEXT")
	private String info_cs;
	@Column(length=50, columnDefinition = "TEXT")
	private String info_da;
	@Column(length=50, columnDefinition = "TEXT")
	private String info_hu;
	@Column(length=50, columnDefinition = "TEXT")
	private String info_ja;
	@Column(length=50, columnDefinition = "TEXT")
	private String info_ko;
	@Column(length=50, columnDefinition = "TEXT")
	private String info_pl;
	@Column(length=50, columnDefinition = "TEXT")
	private String info_pt;
	@Column(length=50, columnDefinition = "TEXT")
	private String info_ru;
	@Column(length=50, columnDefinition = "TEXT")
	private String info_sv;
	@Column(length=50, columnDefinition = "TEXT")
	private String info_tr;
	@Column(length=50, columnDefinition = "TEXT")
	private String info_zh;
	@Column(length=50, columnDefinition = "TEXT")
	private String normalised_code;
	@Column(length=50, columnDefinition = "TEXT")
	private String iata_airport_code;

	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}

	public String getUic() {
		return uic;
	}

	public void setUic(String uic) {
		this.uic = uic;
	}

	public String getUic8_sncf() {
		return uic8_sncf;
	}

	public void setUic8_sncf(String uic8_sncf) {
		this.uic8_sncf = uic8_sncf;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getParent_station_id() {
		return parent_station_id;
	}

	public void setParent_station_id(String parent_station_id) {
		this.parent_station_id = parent_station_id;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCountry_time_zone() {
		return country_time_zone;
	}

	public void setCountry_time_zone(String country_time_zone) {
		this.country_time_zone = country_time_zone;
	}

	public String getIs_city() {
		return is_city;
	}

	public void setIs_city(String is_city) {
		this.is_city = is_city;
	}

	public String getIs_main_station() {
		return is_main_station;
	}

	public void setIs_main_station(String is_main_station) {
		this.is_main_station = is_main_station;
	}

	public String getIs_airport() {
		return is_airport;
	}

	public void setIs_airport(String is_airport) {
		this.is_airport = is_airport;
	}

	public String getIs_suggestable() {
		return is_suggestable;
	}

	public void setIs_suggestable(String is_suggestable) {
		this.is_suggestable = is_suggestable;
	}

	public String getCountry_hint() {
		return country_hint;
	}

	public void setCountry_hint(String country_hint) {
		this.country_hint = country_hint;
	}

	public String getMain_station_hint() {
		return main_station_hint;
	}

	public void setMain_station_hint(String main_station_hint) {
		this.main_station_hint = main_station_hint;
	}

	public String getSncf_id() {
		return sncf_id;
	}

	public void setSncf_id(String sncf_id) {
		this.sncf_id = sncf_id;
	}

	public String getSncf_tvs_id() {
		return sncf_tvs_id;
	}

	public void setSncf_tvs_id(String sncf_tvs_id) {
		this.sncf_tvs_id = sncf_tvs_id;
	}

	public String getSncf_is_enabled() {
		return sncf_is_enabled;
	}

	public void setSncf_is_enabled(String sncf_is_enabled) {
		this.sncf_is_enabled = sncf_is_enabled;
	}

	public String getEntur_id() {
		return entur_id;
	}

	public void setEntur_id(String entur_id) {
		this.entur_id = entur_id;
	}

	public String getEntur_is_enabled() {
		return entur_is_enabled;
	}

	public void setEntur_is_enabled(String entur_is_enabled) {
		this.entur_is_enabled = entur_is_enabled;
	}

	public String getDb_id() {
		return db_id;
	}

	public void setDb_id(String db_id) {
		this.db_id = db_id;
	}

	public String getDb_is_enabled() {
		return db_is_enabled;
	}

	public void setDb_is_enabled(String db_is_enabled) {
		this.db_is_enabled = db_is_enabled;
	}

	public String getBusbud_id() {
		return busbud_id;
	}

	public void setBusbud_id(String busbud_id) {
		this.busbud_id = busbud_id;
	}

	public String getBusbud_is_enabled() {
		return busbud_is_enabled;
	}

	public void setBusbud_is_enabled(String busbud_is_enabled) {
		this.busbud_is_enabled = busbud_is_enabled;
	}

	public String getDistribusion_id() {
		return distribusion_id;
	}

	public void setDistribusion_id(String distribusion_id) {
		this.distribusion_id = distribusion_id;
	}

	public String getDistribusion_is_enabled() {
		return distribusion_is_enabled;
	}

	public void setDistribusion_is_enabled(String distribusion_is_enabled) {
		this.distribusion_is_enabled = distribusion_is_enabled;
	}

	public String getFlixbus_id() {
		return flixbus_id;
	}

	public void setFlixbus_id(String flixbus_id) {
		this.flixbus_id = flixbus_id;
	}

	public String getFlixbus_is_enabled() {
		return flixbus_is_enabled;
	}

	public void setFlixbus_is_enabled(String flixbus_is_enabled) {
		this.flixbus_is_enabled = flixbus_is_enabled;
	}

	public String getCff_id() {
		return cff_id;
	}

	public void setCff_id(String cff_id) {
		this.cff_id = cff_id;
	}

	public String getCff_is_enabled() {
		return cff_is_enabled;
	}

	public void setCff_is_enabled(String cff_is_enabled) {
		this.cff_is_enabled = cff_is_enabled;
	}

	public String getLeoexpress_id() {
		return leoexpress_id;
	}

	public void setLeoexpress_id(String leoexpress_id) {
		this.leoexpress_id = leoexpress_id;
	}

	public String getLeoexpress_is_enabled() {
		return leoexpress_is_enabled;
	}

	public void setLeoexpress_is_enabled(String leoexpress_is_enabled) {
		this.leoexpress_is_enabled = leoexpress_is_enabled;
	}

	public String getObb_id() {
		return obb_id;
	}

	public void setObb_id(String obb_id) {
		this.obb_id = obb_id;
	}

	public String getObb_is_enabled() {
		return obb_is_enabled;
	}

	public void setObb_is_enabled(String obb_is_enabled) {
		this.obb_is_enabled = obb_is_enabled;
	}

	public String getOuigo_id() {
		return ouigo_id;
	}

	public void setOuigo_id(String ouigo_id) {
		this.ouigo_id = ouigo_id;
	}

	public String getOuigo_is_enabled() {
		return ouigo_is_enabled;
	}

	public void setOuigo_is_enabled(String ouigo_is_enabled) {
		this.ouigo_is_enabled = ouigo_is_enabled;
	}

	public String getTrenitalia_id() {
		return trenitalia_id;
	}

	public void setTrenitalia_id(String trenitalia_id) {
		this.trenitalia_id = trenitalia_id;
	}

	public String getTrenitalia_is_enabled() {
		return trenitalia_is_enabled;
	}

	public void setTrenitalia_is_enabled(String trenitalia_is_enabled) {
		this.trenitalia_is_enabled = trenitalia_is_enabled;
	}

	public String getTrenitalia_rtvt_id() {
		return trenitalia_rtvt_id;
	}

	public void setTrenitalia_rtvt_id(String trenitalia_rtvt_id) {
		this.trenitalia_rtvt_id = trenitalia_rtvt_id;
	}

	public String getNtv_rtiv_id() {
		return ntv_rtiv_id;
	}

	public void setNtv_rtiv_id(String ntv_rtiv_id) {
		this.ntv_rtiv_id = ntv_rtiv_id;
	}

	public String getNtv_id() {
		return ntv_id;
	}

	public void setNtv_id(String ntv_id) {
		this.ntv_id = ntv_id;
	}

	public String getNtv_is_enabled() {
		return ntv_is_enabled;
	}

	public void setNtv_is_enabled(String ntv_is_enabled) {
		this.ntv_is_enabled = ntv_is_enabled;
	}

	public String getHkx_id() {
		return hkx_id;
	}

	public void setHkx_id(String hkx_id) {
		this.hkx_id = hkx_id;
	}

	public String getHkx_is_enabled() {
		return hkx_is_enabled;
	}

	public void setHkx_is_enabled(String hkx_is_enabled) {
		this.hkx_is_enabled = hkx_is_enabled;
	}

	public String getRenfe_id() {
		return renfe_id;
	}

	public void setRenfe_id(String renfe_id) {
		this.renfe_id = renfe_id;
	}

	public String getRenfe_is_enabled() {
		return renfe_is_enabled;
	}

	public void setRenfe_is_enabled(String renfe_is_enabled) {
		this.renfe_is_enabled = renfe_is_enabled;
	}

	public String getAtoc_id() {
		return atoc_id;
	}

	public void setAtoc_id(String atoc_id) {
		this.atoc_id = atoc_id;
	}

	public String getAtoc_is_enabled() {
		return atoc_is_enabled;
	}

	public void setAtoc_is_enabled(String atoc_is_enabled) {
		this.atoc_is_enabled = atoc_is_enabled;
	}

	public String getBenerail_id() {
		return benerail_id;
	}

	public void setBenerail_id(String benerail_id) {
		this.benerail_id = benerail_id;
	}

	public String getBenerail_is_enabled() {
		return benerail_is_enabled;
	}

	public void setBenerail_is_enabled(String benerail_is_enabled) {
		this.benerail_is_enabled = benerail_is_enabled;
	}

	public String getWestbahn_id() {
		return westbahn_id;
	}

	public void setWestbahn_id(String westbahn_id) {
		this.westbahn_id = westbahn_id;
	}

	public String getWestbahn_is_enabled() {
		return westbahn_is_enabled;
	}

	public void setWestbahn_is_enabled(String westbahn_is_enabled) {
		this.westbahn_is_enabled = westbahn_is_enabled;
	}

	public String getSncf_self_service_machine() {
		return sncf_self_service_machine;
	}

	public void setSncf_self_service_machine(String sncf_self_service_machine) {
		this.sncf_self_service_machine = sncf_self_service_machine;
	}

	public String getSame_as() {
		return same_as;
	}

	public void setSame_as(String same_as) {
		this.same_as = same_as;
	}

	public String getInfo_de() {
		return info_de;
	}

	public void setInfo_de(String info_de) {
		this.info_de = info_de;
	}

	public String getInfo_en() {
		return info_en;
	}

	public void setInfo_en(String info_en) {
		this.info_en = info_en;
	}

	public String getInfo_es() {
		return info_es;
	}

	public void setInfo_es(String info_es) {
		this.info_es = info_es;
	}

	public String getInfo_fr() {
		return info_fr;
	}

	public void setInfo_fr(String info_fr) {
		this.info_fr = info_fr;
	}

	public String getInfo_it() {
		return info_it;
	}

	public void setInfo_it(String info_it) {
		this.info_it = info_it;
	}

	public String getInfo_nb() {
		return info_nb;
	}

	public void setInfo_nb(String info_nb) {
		this.info_nb = info_nb;
	}

	public String getInfo_nl() {
		return info_nl;
	}

	public void setInfo_nl(String info_nl) {
		this.info_nl = info_nl;
	}

	public String getInfo_cs() {
		return info_cs;
	}

	public void setInfo_cs(String info_cs) {
		this.info_cs = info_cs;
	}

	public String getInfo_da() {
		return info_da;
	}

	public void setInfo_da(String info_da) {
		this.info_da = info_da;
	}

	public String getInfo_hu() {
		return info_hu;
	}

	public void setInfo_hu(String info_hu) {
		this.info_hu = info_hu;
	}

	public String getInfo_ja() {
		return info_ja;
	}

	public void setInfo_ja(String info_ja) {
		this.info_ja = info_ja;
	}

	public String getInfo_ko() {
		return info_ko;
	}

	public void setInfo_ko(String info_ko) {
		this.info_ko = info_ko;
	}

	public String getInfo_pl() {
		return info_pl;
	}

	public void setInfo_pl(String info_pl) {
		this.info_pl = info_pl;
	}

	public String getInfo_pt() {
		return info_pt;
	}

	public void setInfo_pt(String info_pt) {
		this.info_pt = info_pt;
	}

	public String getInfo_ru() {
		return info_ru;
	}

	public void setInfo_ru(String info_ru) {
		this.info_ru = info_ru;
	}

	public String getInfo_sv() {
		return info_sv;
	}

	public void setInfo_sv(String info_sv) {
		this.info_sv = info_sv;
	}

	public String getInfo_tr() {
		return info_tr;
	}

	public void setInfo_tr(String info_tr) {
		this.info_tr = info_tr;
	}

	public String getInfo_zh() {
		return info_zh;
	}

	public void setInfo_zh(String info_zh) {
		this.info_zh = info_zh;
	}

	public String getNormalised_code() {
		return normalised_code;
	}

	public void setNormalised_code(String normalised_code) {
		this.normalised_code = normalised_code;
	}

	public String getIata_airport_code() {
		return iata_airport_code;
	}

	public void setIata_airport_code(String iata_airport_code) {
		this.iata_airport_code = iata_airport_code;
	}

}

