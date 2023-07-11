package com.drill.domain;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "USER")
public class User {
	@Column(nullable = false, length = 30, unique = true)
	private int num; // 일련번호
	@Id
	private String userid; // 아이디
	@Column(nullable = false, length = 30)
	private String password; // 비밀번호
	@Column(nullable = false, length = 50)
	private String email; // 이메일
	@Enumerated(EnumType.STRING)
	private UserRoleStatus role;
	@CreationTimestamp
	private Timestamp regTM; // 가입시간
}
