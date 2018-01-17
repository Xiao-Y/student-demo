<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="${namespace}">
	<resultMap id="BaseResultMap" type="${type}">
<#assign keys=columns?keys/>
<#list keys as key>
	<#assign columnModel=columns[key]/>
	<#if columnModel.isPk>
		<id column="${columnModel.columnName}" property="${columnModel.fieldName}" jdbcType="${columnModel.mybatisType}" />
	</#if>
</#list>
<#list keys as key>
	<#assign columnModel=columns[key]/>
	<#if !columnModel.isPk>
        <result column="${columnModel.columnName}" property="${columnModel.fieldName}" jdbcType="${columnModel.mybatisType}" />
	</#if>
</#list>
	</resultMap>
	<sql id="base_column_list">
		${columnStr}
	</sql>
	<!-- 公用查询条件 -->
	<sql id="selectCondition">
		<where>
		<#list keys as key>
			<#assign columnModel=columns[key]/>
			<if test="null != ${columnModel.fieldName} and '' != ${columnModel.fieldName}">
				and ${columnModel.columnName} = ${r'#{'}${columnModel.fieldName}${r'}'}
			</if>
		</#list>
		</where>
	</sql>
	<select id="selectAll" resultMap="BaseResultMap" parameterType="${type}">
		select
		<include refid="base_column_list" />
		from ${tableName}
		<include refid="selectCondition" />
	</select>
	<select id="selectAllCount" resultType="java.lang.Integer" parameterType="${type}">
		select count(*)
		from ${tableName}
		<include refid="selectCondition" />
	</select>
	<select id="selectByPrimaryKey" resultMap="BaseResultMap" parameterType="${type}">
		select
		<include refid="base_column_list" />
		from ${tableName}
		where 1=1
<#list keys as key>
	<#assign columnModel=columns[key]/>
	<#if columnModel.isPk>
		and ${columnModel.columnName} = ${r'#{'}${columnModel.fieldName}}
	</#if>
</#list>
	</select>
	<delete id="deleteByPrimaryKey" parameterType="${type}">
		delete from ${tableName}
		where 1=1
<#list keys as key>
	<#assign columnModel=columns[key]/>
	<#if columnModel.isPk>
		and ${columnModel.columnName} = ${r'#{'}${columnModel.fieldName}}
	</#if>
</#list>
	</delete>
	<insert id="insert" parameterType="${type}">
		insert into ${tableName} (${columnStr})
		values (
		<#list keys as key>
			<#assign columnModel=columns[key]/>
			${r'#{'}${columnModel.fieldName},jdbcType=${columnModel.mybatisType}}<#if key_has_next>,</#if>
		</#list>
		)
	</insert>
	<insert id="insertSelective" parameterType="${type}">
		insert into ${tableName}
		<trim prefix="(" suffix=")" suffixOverrides=",">
		<#list keys as key>
			<#assign columnModel=columns[key]/>
			<if test="null != ${columnModel.fieldName}">
				${columnModel.columnName},
			</if>
		</#list>
		</trim>
		<trim prefix="values (" suffix=")" suffixOverrides=",">
		<#list keys as key>
			<#assign columnModel=columns[key]/>
			<if test="null != ${columnModel.fieldName}">
				${r'#{'}${columnModel.fieldName},jdbcType=${columnModel.mybatisType}},
			</if>
		</#list>
		</trim>
	</insert>
	<update id="updateByPrimaryKeySelective" parameterType="${type}">
		update ${tableName}
		<set>
		<#list keys as key>
			<#assign columnModel=columns[key]/>
			<if test="null != ${columnModel.fieldName}">
				${columnModel.columnName} = ${r'#{'}${columnModel.fieldName},jdbcType=${columnModel.mybatisType}},
			</if>
		</#list>
		</set>
		where 1=1
<#list keys as key>
	<#assign columnModel=columns[key]/>
	<#if columnModel.isPk>
		and ${columnModel.columnName} = ${r'#{'}${columnModel.fieldName}}
	</#if>
</#list>
	</update>
	<update id="updateByPrimaryKey" parameterType="${type}">
		update ${tableName}
		set
	<#list keys as key>
		<#assign columnModel=columns[key]/>
		<#if !columnModel.isPk>
			${columnModel.columnName} = ${r'#{'}${columnModel.fieldName},jdbcType=${columnModel.mybatisType}}<#if key_has_next>,</#if>
		</#if>
	</#list>
		where 1=1
<#list keys as key>
	<#assign columnModel=columns[key]/>
	<#if columnModel.isPk>
		and ${columnModel.columnName} = ${r'#{'}${columnModel.fieldName}}
	</#if>
</#list>
	</update>
</mapper>