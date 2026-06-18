<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
	"http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="ns.sql.BookMapper">
		<sql id="select-book">
		
	</sql>
	<insert id="insertBook">	
	</insert>
	
	<select id="getBooks">		
	</select>
	
	<select id="searchByTitle">		
	</select>
	
	<select id="searchByPublisher">		
	</select>
	
	<!-- 특정 가격 이상인 책만 검색 -->
	<select id="searchByPrice">       
    </select>
	
	<select id="searchByIsbn">		
	</select>
	
	<delete id="delete">		
	</delete>
	
	<select id="getIsbn">		
	</select>
	
	<!--  특정 isbn에 해당하는 Book의 title,author, description 정보 수정하기 -->
	<update id="update">		
	</update>
</mapper>

 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 