package com.cmstechub.resume.config;

import com.cmstechub.resume.entity.ResumeProfile;
import com.cmstechub.resume.entity.Service;
import com.cmstechub.resume.repository.ResumeProfileRepository;
import com.cmstechub.resume.repository.ServiceRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner seedData(ResumeProfileRepository profileRepo, ServiceRepository serviceRepo) {
        return args -> {
            // 1. Seed Resume Profile (if empty)
            if (profileRepo.count() == 0) {
                ResumeProfile p = new ResumeProfile();
                p.setFullName("Calistus Masinde Sirucha");
                p.setTitle("IT Student · Full-Stack Developer · Kali Linux Enthusiast");
                p.setSummary(
                    "Third-year IT student at Kaimosi Friends University. " +
                    "CMSTechub is my personal brand — C.M.S. are my initials, and Techub reflects " +
                    "my passion for technology. I build web applications with Java, Spring Boot, " +
                    "and modern front-end tooling, and I'm currently sharpening my skills in " +
                    "Kali Linux, Python automation, and cloud databases."
                );
                p.setEmail("masindecalistus411@gmail.com");
                p.setLinkedin("https://www.linkedin.com/in/calistus-sirucha-a77b06421/");
                p.setGithub("https://github.com/NativeRegex");
                profileRepo.save(p);
                System.out.println("✅ Seeded CMSTechub profile for Calistus.");
            }

            // 2. Seed IT Services (if empty)
            if (serviceRepo.count() == 0) {
                Service s1 = new Service();
                s1.setTitle("Web Development (Spring Boot)");
                s1.setDescription("Custom web applications built with Java and Spring Boot. Includes database setup and hosting.");
                s1.setPrice("$150");
                s1.setCategory("Development");
                serviceRepo.save(s1);

                Service s2 = new Service();
                s2.setTitle("Kali Linux Tutoring");
                s2.setDescription("One-on-one sessions on ethical hacking, penetration testing, and Linux fundamentals.");
                s2.setPrice("$30/hr");
                s2.setCategory("Security");
                serviceRepo.save(s2);

                Service s3 = new Service();
                s3.setTitle("Database Design");
                s3.setDescription("Design and optimization for MySQL, PostgreSQL, or Supabase databases.");
                s3.setPrice("$80");
                s3.setCategory("Database");
                serviceRepo.save(s3);
                
                Service s4 = new Service();
                s4.setTitle("IT Support & PC Repair");
                s4.setDescription("Software installation, virus removal, hardware troubleshooting, and OS setup.");
                s4.setPrice("$20/hr");
                s4.setCategory("Support");
                serviceRepo.save(s4);
                System.out.println("✅ Seeded CMSTechub IT Services.");
            }
        };
    }
}
