package com.hpkj.process.entity;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * ActGeBytearray entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ACT_GE_BYTEARRAY", schema = "HPOA")
public class ActGeBytearray implements java.io.Serializable {

	// Fields

	private String id;
	private ActReDeployment actReDeployment;
	private BigDecimal rev;
	private String name;
	private String bytes;
	private Boolean generated;
	private Set<ActReModel> actReModelsForEditorSourceValueId = new HashSet<ActReModel>(
			0);
	private Set<ActRuJob> actRuJobs = new HashSet<ActRuJob>(0);
	private Set<ActRuVariable> actRuVariables = new HashSet<ActRuVariable>(0);
	private Set<ActReModel> actReModelsForEditorSourceExtraValueId = new HashSet<ActReModel>(
			0);
	private Set<ActProcdefInfo> actProcdefInfos = new HashSet<ActProcdefInfo>(0);

	// Constructors

	/** default constructor */
	public ActGeBytearray() {
	}

	/** minimal constructor */
	public ActGeBytearray(String id) {
		this.id = id;
	}

	/** full constructor */
	public ActGeBytearray(String id, ActReDeployment actReDeployment,
			BigDecimal rev, String name, String bytes, Boolean generated,
			Set<ActReModel> actReModelsForEditorSourceValueId,
			Set<ActRuJob> actRuJobs, Set<ActRuVariable> actRuVariables,
			Set<ActReModel> actReModelsForEditorSourceExtraValueId,
			Set<ActProcdefInfo> actProcdefInfos) {
		this.id = id;
		this.actReDeployment = actReDeployment;
		this.rev = rev;
		this.name = name;
		this.bytes = bytes;
		this.generated = generated;
		this.actReModelsForEditorSourceValueId = actReModelsForEditorSourceValueId;
		this.actRuJobs = actRuJobs;
		this.actRuVariables = actRuVariables;
		this.actReModelsForEditorSourceExtraValueId = actReModelsForEditorSourceExtraValueId;
		this.actProcdefInfos = actProcdefInfos;
	}

	// Property accessors
	@Id
	@Column(name = "ID_", unique = true, nullable = false)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DEPLOYMENT_ID_")
	public ActReDeployment getActReDeployment() {
		return this.actReDeployment;
	}

	public void setActReDeployment(ActReDeployment actReDeployment) {
		this.actReDeployment = actReDeployment;
	}

	@Column(name = "REV_", precision = 22, scale = 0)
	public BigDecimal getRev() {
		return this.rev;
	}

	public void setRev(BigDecimal rev) {
		this.rev = rev;
	}

	@Column(name = "NAME_")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "BYTES_")
	public String getBytes() {
		return this.bytes;
	}

	public void setBytes(String bytes) {
		this.bytes = bytes;
	}

	@Column(name = "GENERATED_", precision = 1, scale = 0)
	public Boolean getGenerated() {
		return this.generated;
	}

	public void setGenerated(Boolean generated) {
		this.generated = generated;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "actGeBytearrayByEditorSourceValueId")
	public Set<ActReModel> getActReModelsForEditorSourceValueId() {
		return this.actReModelsForEditorSourceValueId;
	}

	public void setActReModelsForEditorSourceValueId(
			Set<ActReModel> actReModelsForEditorSourceValueId) {
		this.actReModelsForEditorSourceValueId = actReModelsForEditorSourceValueId;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "actGeBytearray")
	public Set<ActRuJob> getActRuJobs() {
		return this.actRuJobs;
	}

	public void setActRuJobs(Set<ActRuJob> actRuJobs) {
		this.actRuJobs = actRuJobs;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "actGeBytearray")
	public Set<ActRuVariable> getActRuVariables() {
		return this.actRuVariables;
	}

	public void setActRuVariables(Set<ActRuVariable> actRuVariables) {
		this.actRuVariables = actRuVariables;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "actGeBytearrayByEditorSourceExtraValueId")
	public Set<ActReModel> getActReModelsForEditorSourceExtraValueId() {
		return this.actReModelsForEditorSourceExtraValueId;
	}

	public void setActReModelsForEditorSourceExtraValueId(
			Set<ActReModel> actReModelsForEditorSourceExtraValueId) {
		this.actReModelsForEditorSourceExtraValueId = actReModelsForEditorSourceExtraValueId;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "actGeBytearray")
	public Set<ActProcdefInfo> getActProcdefInfos() {
		return this.actProcdefInfos;
	}

	public void setActProcdefInfos(Set<ActProcdefInfo> actProcdefInfos) {
		this.actProcdefInfos = actProcdefInfos;
	}

}