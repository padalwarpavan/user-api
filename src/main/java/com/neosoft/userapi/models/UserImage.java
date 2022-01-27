package com.neosoft.userapi.models;

import java.util.Arrays;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user_Image")
public class UserImage {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "userimageid", length = 10)
	private Integer userImageId;
	@Column(name = "filename", length = 100)
	private String fileName;
	@Column(name = "filetype", length = 100)
	private String fileType;
	@Lob
	@Column(name = "userimage")
	private byte[] userImage;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="userdetails", referencedColumnName = "id")
	private UserDetails userDetails;
	
	public UserImage() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserImage(Integer userImageId, String fileName, String fileType, byte[] userImage) {
		super();
		this.userImageId = userImageId;
		this.fileName = fileName;
		this.fileType = fileType;
		this.userImage = userImage;
	}
	public Integer getUserImageId() {
		return userImageId;
	}
	public void setUserImageId(Integer userImageId) {
		this.userImageId = userImageId;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public byte[] getUserImage() {
		return userImage;
	}
	public void setUserImage(byte[] userImage) {
		this.userImage = userImage;
	}
	
	public UserDetails getUserDetails() {
		return userDetails;
	}
	public void setUserDetails(UserDetails userDetails) {
		this.userDetails = userDetails;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(userImage);
		result = prime * result + Objects.hash(fileName, fileType, userDetails, userImageId);
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserImage other = (UserImage) obj;
		return Objects.equals(fileName, other.fileName) && Objects.equals(fileType, other.fileType)
				&& Objects.equals(userDetails, other.userDetails) && Arrays.equals(userImage, other.userImage)
				&& Objects.equals(userImageId, other.userImageId);
	}
	@Override
	public String toString() {
		return "UserImage [userImageId=" + userImageId + ", fileName=" + fileName + ", fileType=" + fileType
				+ ", userImage=" + Arrays.toString(userImage) + ", userDetails=" + userDetails + "]";
	}
	
	

}
