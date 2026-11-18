package com.cmstechub.resume.config;

import com.cmstechub.resume.entity.ResumeProfile;
import com.cmstechub.resume.repository.ResumeProfileRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner seedData(ResumeProfileRepository repo) {
        return args -> {
            if (repo.count() == 0) {
                ResumeProfile p = new ResumeProfile();
                p.setFullName("Calistus Masinde Sirucha");
                p.setTitle("IT Specialist · Full-Stack Developer · IT Services Provider");
                p.setLocation("Kakamega, Kenya");

                p.setSummary(
                    "I'm Calistus Masinde Sirucha, a third-year IT student at Kaimosi Friends University " +
                    "and the founder of CMSTechub — a personal brand built on delivering real, working technology. " +
                    "I build full-stack web applications with Java and Spring Boot, design and optimize databases, " +
                    "and offer a wide range of IT services — from web development and networking to hardware repair, " +
                    "cybersecurity, and IT tutoring. My focus is simple: solve real problems with clean, secure, " +
                    "production-grade technology."
                );

                p.setExperience(
                    "• Industrial Attachment (3 months) — Hands-on exposure to system administration, " +
                    "database maintenance, LAN configuration, and IT support for campus users.\n\n" +
                    "• CMSTechub Services (2024–present) — Founded and operate a personal IT services business " +
                    "covering web development, networking, hardware repair, cybersecurity, and tutoring.\n\n" +
                    "• KAFU Sports Portal (2025) — Built a full-stack university sports platform with Spring Boot " +
                    "REST API, PostgreSQL database, React admin dashboard, and a public Netlify frontend.\n\n" +
                    "• CMSTechub Portfolio (2024–present) — Designed and deployed a personal portfolio with " +
                    "Thymeleaf, JWT auth, PostgreSQL, and live typewriter animation."
                );

                p.setEducation(
                    "Bachelor of Science in Information Technology — Kaimosi Friends University (Year 3)\n\n" +
                    "Focus areas: Full-stack development (Spring Boot + React), database design, Linux systems, " +
                    "Kali Linux security testing, computer networking, and machine learning fundamentals.\n\n" +
                    "Certifications in progress: Java & Spring Boot, Ethical Hacking (Kali Linux)"
                );

                p.setEmail("masindecalistus411@gmail.com");
                p.setLinkedin("https://www.linkedin.com/in/calistus-sirucha-a77b06421/");
                p.setGithub("https://github.com/NativeRegex");

                repo.save(p);
                System.out.println("✅ Seeded CMSTechub profile");
            }
        };
    }
}
