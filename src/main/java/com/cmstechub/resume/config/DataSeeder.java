package com.cmstechub.resume.config;

import com.cmstechub.resume.entity.ResumeProfile;
import com.cmstechub.resume.repository.ResumeProfileRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner seedData(ResumeProfileRepository repository) {
        return args -> {
            if (repository.count() == 0) {
                ResumeProfile p = new ResumeProfile();
                p.setFullName("Calistus Masinde Sirucha");
                p.setTitle("IT Student · Full-Stack Developer · Kali Linux Enthusiast");
                p.setSummary(
                    "Third-year IT student at Kaimosi Friends University. " +
                    "CMSTechub is my personal brand — C.M.S. are my initials, and Techub reflects " +
                    "my passion for technology. I build web applications with Java, Spring Boot, " +
                    "and modern front-end tooling, and I'm currently sharpening my skills in " +
                    "Kali Linux, Python automation, and cloud databases. I enjoy turning ideas " +
                    "into working software and helping others learn IT along the way."
                );
                p.setEmail("masindecalistus411@gmail.com");
                p.setLinkedin("https://www.linkedin.com/in/calistus-sirucha-a77b06421/");
                p.setGithub("https://github.com/NativeRegex");
                repository.save(p);
                System.out.println("✅ Seeded CMSTechub profile for Calistus.");
            }
        };
    }
}
