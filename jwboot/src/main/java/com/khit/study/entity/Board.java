package com.khit.study.entity;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Board {
	@Id //primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) //자동순번
	private Long id;
	@Column(length=400, nullable=false)
	private String title;
	@Column(length=30, nullable=false)
	private String writer;
	@Column(length=4000, nullable=false)
	private String content;
	@CreationTimestamp //현재 날짜와 시간 자동 생성
	private Timestamp createDate;
	
}
