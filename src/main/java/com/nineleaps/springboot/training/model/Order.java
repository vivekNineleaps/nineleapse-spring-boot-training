package com.nineleaps.springboot.training.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="NINELEAPS_ORDER")
@NoArgsConstructor
public @Data class Order extends RepresentationModel<Order>{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ORDERID")
	private Long orderid;
	@Column(name="ORDERDESCRIPTION")
	private String orderDescription;
	
	@ManyToOne(fetch=  FetchType.LAZY)
	@JsonIgnore
	private User user;
	
}
