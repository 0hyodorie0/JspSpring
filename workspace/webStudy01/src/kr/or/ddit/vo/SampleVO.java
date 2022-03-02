package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.Arrays;

/**
 * 객체 == 객체, 객체.equals(객체)
 * 
 *
 */
public class SampleVO implements Serializable {
	private String code; // 학번
	private String name;
	private String birth;
	private Integer age;
	private String grade;
	private String[] license;
	private String career;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String[] getLicense() {
		return license;
	}
	public void setLicense(String[] license) {
		this.license = license;
	}
	public String getCareer() {
		return career;
	}
	public void setCareer(String career) {
		this.career = career;
	}
	@Override
	public String toString() {
		return "SampleVO [code=" + code + ", name=" + name + ", age=" + age + "]";
	}
	
	
}
