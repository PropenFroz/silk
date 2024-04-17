package com.a03.silk;

import com.a03.silk.model.GradeKursus;
import com.a03.silk.model.Guru;
import com.a03.silk.model.JurusanKursus;
import com.a03.silk.model.Role;
import com.a03.silk.model.UserModel;
import com.a03.silk.repository.GradeKursusDb;
import com.a03.silk.repository.GuruDb;
import com.a03.silk.repository.JurusanKursusDb;
import com.a03.silk.repository.RoleDb;
import com.a03.silk.repository.UserDb;
import com.a03.silk.restservice.UserRestService;
import com.a03.silk.service.RoleService;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	@Transactional
	CommandLineRunner run(RoleDb roleDb, UserDb userDb, GradeKursusDb gradeKursusDb, JurusanKursusDb jurusanKursusDb, GuruDb guruDb, RoleService roleService, UserRestService userRestService) {
		return args -> {
			if (roleDb.findAll().size() == 0) {
				Role admin = new Role();
				admin.setRole("Admin");
				roleDb.save(admin);

				Role karyawan = new Role();
				karyawan.setRole("Karyawan");
				roleDb.save(karyawan);

				Role eksekutif = new Role();
				eksekutif.setRole("Eksekutif");
				roleDb.save(eksekutif);

				Role guru = new Role();
				guru.setRole("Guru");
				roleDb.save(guru);
			}

			if (userDb.findAll().size() == 0) {
				UserModel adminSafa = new UserModel();
				adminSafa.setRole(roleService.getRoleByRoleName("Admin"));
				adminSafa.setUsername("shafanjw");
				adminSafa.setPassword(userRestService.encryptPassword("Najwaq27"));
				adminSafa.setName("Shafa");

				userDb.save(adminSafa);
			}

			if (gradeKursusDb.findAll().size() == 0) {
				GradeKursus gradeKursus = new GradeKursus();
				gradeKursus.setNamaGrade("Prep A");
				gradeKursusDb.save(gradeKursus);
			}

			if (jurusanKursusDb.findAll().size() == 0) {
				JurusanKursus jurusanKursus = new JurusanKursus();
				jurusanKursus.setNamaJurusan("Vocal");
				jurusanKursusDb.save(jurusanKursus);
			}

			if (guruDb.findAll().size() == 0) {
				Guru guru = new Guru();
				guru.setNamaGuru("Ahmad Dhani");
				guruDb.save(guru);
			}
		};
	}
}
