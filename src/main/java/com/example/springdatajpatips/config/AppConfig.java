package com.example.springdatajpatips.config;

import com.example.springdatajpatips.model.Company;
import com.example.springdatajpatips.response.CompanyProfileResponse;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class AppConfig implements WebMvcConfigurer {

	@Bean
	MapperFacade configure() {
		MapperFactory mapperFactory = new DefaultMapperFactory.Builder().mapNulls(false).build();
		registerMappings(mapperFactory);
		return mapperFactory.getMapperFacade();
	}

	private void registerMappings(MapperFactory mapperFactory) {

//		mapperFactory.classMap(CompanyRequestDTO.class, Company.class)
//				.field("profile.name", "name")
//				.field("profile.city", "city")
//				.field("profile.state", "state")
//				.field("profile.parentCompanyName", "parentCompanyName")
//				.field("profile.groupId", "groupId")
//				.field("profile.companyUrl", "companyUrl")
//				.field("profile.companyDomain", "companyDomain")
//				.field("profile.sendEmailToTerritoryAdmin", "sendEmailToTerritoryAdmin")
//				.exclude("profile.isLocked")
//				.exclude("profile.territoryId")
//				.exclude("profile.countryId").byDefault().register();
//
//		mapperFactory.classMap(CompanyProfile.class, Company.class)
//				.exclude("territoryId")
//				.exclude("countryId").byDefault().register();
//
		mapperFactory.classMap(Company.class, CompanyProfileResponse.class)
				.field("territory.name", "territory")
				.field("territory.id", "territoryId")
				.field("country.name", "country")
				.field("country.id", "countryId").byDefault().register();


	}

		@Bean
		AuditorAware<Long> auditorAware() {
			return new AuditorAwareImpl();
		}
}
