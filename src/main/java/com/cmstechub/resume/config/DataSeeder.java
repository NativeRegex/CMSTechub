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
            // ---- 1. Resume Profile ----
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
                System.out.println("✅ Seeded CMSTechub profile");
            }

            // ---- 2. Services ----
            if (serviceRepo.count() == 0) {
                Service s;

                // Web Development
                s = new Service();
                s.setTitle("Web Development");
                s.setDescription("Custom websites and web applications built with Spring Boot, React, HTML, CSS, and JavaScript. From landing pages to full admin dashboards.");
                s.setPrice("From KES 15,000");
                s.setCategory("Development");
                serviceRepo.save(s);

                // Networking
                s = new Service();
                s.setTitle("Networking & Network Setup");
                s.setDescription("LAN/WAN setup, router and switch configuration, Wi-Fi deployment, network security hardening, and troubleshooting for homes and small businesses.");
                s.setPrice("From KES 8,000");
                s.setCategory("Infrastructure");
                serviceRepo.save(s);

                // Hardware repair
                s = new Service();
                s.setTitle("Computer Hardware Repair");
                s.setDescription("Diagnostics, component replacement, PC assembly, and upgrades for desktops and laptops. Expert handling of RAM, SSD, motherboard, and power issues.");
                s.setPrice("From KES 1,500");
                s.setCategory("Hardware");
                serviceRepo.save(s);

                // Software installation
                s = new Service();
                s.setTitle("Software Installation & Recovery");
                s.setDescription("Windows and Linux installation, driver setup, virus removal, data backup, and system recovery. Complete OS configuration and optimization.");
                s.setPrice("From KES 1,000");
                s.setCategory("Software");
                serviceRepo.save(s);

                // Cybersecurity
                s = new Service();
                s.setTitle("Cybersecurity & Ethical Hacking");
                s.setDescription("Vulnerability assessments, penetration testing, and security audits using Kali Linux tools — nmap, Metasploit, Wireshark, and Burp Suite.");
                s.setPrice("From KES 10,000");
                s.setCategory("Security");
                serviceRepo.save(s);

                // Database design
                s = new Service();
                s.setTitle("Database Design & Management");
                s.setDescription("Design, optimization, and management of MySQL, PostgreSQL, Supabase, and H2 databases. Schema design, indexing, and query performance tuning.");
                s.setPrice("From KES 6,000");
                s.setCategory("Database");
                serviceRepo.save(s);

                // IT tutoring
                s = new Service();
                s.setTitle("IT Tutoring & Training");
                s.setDescription("One-on-one tutoring on Java, Python, C, C++, Linux basics, web development, and cybersecurity fundamentals. Beginner to intermediate levels.");
                s.setPrice("From KES 2,000/hr");
                s.setCategory("Training");
                serviceRepo.save(s);

                // IT consultancy
                s = new Service();
                s.setTitle("IT Consultancy");
                s.setDescription("Technology audits, digital transformation advice, and IT cost optimization for small businesses and startups.");
                s.setPrice("Custom quote");
                s.setCategory("Consulting");
                serviceRepo.save(s);

                // Data backup & recovery
                s = new Service();
                s.setTitle("Data Backup & Recovery");
                s.setDescription("Reliable backup solutions and emergency data recovery services. Cloud backup configuration, local backup setup, and disaster recovery planning.");
                s.setPrice("From KES 3,000");
                s.setCategory("Data");
                serviceRepo.save(s);

                // Printer & peripheral repair
                s = new Service();
                s.setTitle("Printer & Peripheral Repair");
                s.setDescription("Printer installation, configuration, and repair. Scanners, projectors, and other IT peripherals setup and troubleshooting.");
                s.setPrice("From KES 1,200");
                s.setCategory("Hardware");
                serviceRepo.save(s);

                // Email & cloud setup
                s = new Service();
                s.setTitle("Email & Cloud Setup");
                s.setDescription("Business email configuration, Google Workspace, Microsoft 365, cloud storage setup, and email migration.");
                s.setPrice("From KES 4,000");
                s.setCategory("Cloud");
                serviceRepo.save(s);

                // Device setup & configuration
                s = new Service();
                s.setTitle("New Device Setup & Configuration");
                s.setDescription("Complete setup of new laptops, desktops, and phones — OS installation, software, security, user accounts, and backup configuration.");
                s.setPrice("From KES 1,500");
                s.setCategory("Support");
                serviceRepo.save(s);

                System.out.println("✅ Seeded 12 CMSTechub services");
            }
        };
    }
}
