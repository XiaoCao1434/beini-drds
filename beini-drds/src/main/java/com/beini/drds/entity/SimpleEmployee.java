package com.beini.drds.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SimpleEmployee {
	@ApiModelProperty(value="主键ID,如果新增时，可以不填写，如果是更新则必须为已有的ID")
	@Id
	@GeneratedValue
	private Integer id;
	private String name1;
	private String name2;
	private String name3;
	private String name4;
	private String name5;
	private String name6;
	private String name7;
	private String name8;
	private String name9;
	
}
