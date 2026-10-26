/* =========================================================
   CMSTechub — "Digital Rainstorm" background
   Layer A: Binary/hex rain (green→cyan gradient)
   Layer B: Network particle mesh
   Layer C: Occasional packet bursts
   Layer D: CRT scanline sweep
   ========================================================= */

(() => {
    const canvas = document.getElementById('matrix');
    if (!canvas) return;
    const ctx = canvas.getContext('2d');

    let W = 0, H = 0;

    // ---------- Layer A: Rain ----------
    const FONT_SIZE = 15;
    const CHARS = '01 0123456789ABCDEF'.split('');
    let columns = 0;
    let drops = [];   // { y, speed, trail }

    function initRain() {
        columns = Math.floor(W / FONT_SIZE);
        drops = [];
        for (let i = 0; i < columns; i++) {
            drops.push({
                y: Math.random() * -H,
                speed: 1.5 + Math.random() * 3.5,
                chars: Array.from({ length: 18 }, () =>
                    CHARS[Math.floor(Math.random() * CHARS.length)]
                ),
                hueShift: Math.random() * 20
            });
        }
    }

    function drawRain() {
        ctx.font = FONT_SIZE + "px 'Fira Code', monospace";
        ctx.textBaseline = 'top';

        for (let i = 0; i < drops.length; i++) {
            const d = drops[i];
            const x = i * FONT_SIZE;

            for (let j = 0; j < d.chars.length; j++) {
                const cy = d.y + j * FONT_SIZE;
                if (cy < 0 || cy > H) continue;

                const t = j / d.chars.length;
                // Head is bright white-green, tail fades to cyan
                let color;
                if (j === 0) {
                    color = 'rgba(220, 255, 230, 0.95)';
                } else if (j < 3) {
                    color = 'rgba(120, 255, 170, ' + (0.9 - t * 0.4) + ')';
                } else {
                    const g = Math.round(255 - t * 80);
                    const b = Math.round(150 + t * 100);
                    color = `rgba(0, ${g}, ${b}, ${0.55 - t * 0.45})`;
                }
                ctx.fillStyle = color;
                ctx.fillText(d.chars[j], x, cy);
            }

            d.y += d.speed;

            // Reset drop when fully off screen
            if (d.y > H + d.chars.length * FONT_SIZE) {
                d.y = -Math.random() * 200;
                d.speed = 1.5 + Math.random() * 3.5;
            }

            // Occasionally mutate a character mid-fall
            if (Math.random() > 0.98) {
                d.chars[Math.floor(Math.random() * d.chars.length)] =
                    CHARS[Math.floor(Math.random() * CHARS.length)];
            }
        }
    }

    // ---------- Layer B: Particle network ----------
    const particles = [];
    const PARTICLE_COUNT = 55;

    function initParticles() {
        particles.length = 0;
        for (let i = 0; i < PARTICLE_COUNT; i++) {
            particles.push({
                x: Math.random() * W,
                y: Math.random() * H,
                vx: (Math.random() - 0.5) * 0.4,
                vy: (Math.random() - 0.5) * 0.4,
                r: 1.5 + Math.random() * 1.5
            });
        }
    }

    function drawParticles() {
        // Move
        for (const p of particles) {
            p.x += p.vx;
            p.y += p.vy;
            if (p.x < 0 || p.x > W) p.vx *= -1;
            if (p.y < 0 || p.y > H) p.vy *= -1;
        }

        // Lines between close particles
        for (let i = 0; i < particles.length; i++) {
            for (let j = i + 1; j < particles.length; j++) {
                const a = particles[i], b = particles[j];
                const dx = a.x - b.x, dy = a.y - b.y;
                const dist2 = dx * dx + dy * dy;
                if (dist2 < 120 * 120) {
                    const alpha = (1 - Math.sqrt(dist2) / 120) * 0.18;
                    ctx.strokeStyle = `rgba(0, 255, 180, ${alpha})`;
                    ctx.lineWidth = 0.6;
                    ctx.beginPath();
                    ctx.moveTo(a.x, a.y);
                    ctx.lineTo(b.x, b.y);
                    ctx.stroke();
                }
            }
        }

        // Dots
        for (const p of particles) {
            ctx.fillStyle = 'rgba(120, 255, 200, 0.55)';
            ctx.beginPath();
            ctx.arc(p.x, p.y, p.r, 0, Math.PI * 2);
            ctx.fill();
        }
    }

    // ---------- Layer C: Packet bursts ----------
    const bursts = [];

    function maybeSpawnBurst() {
        if (Math.random() < 0.008 && bursts.length < 3) {
            bursts.push({
                x: Math.random() * W,
                y: Math.random() * H,
                r: 0,
                max: 180 + Math.random() * 120,
                alpha: 0.9
            });
        }
    }

    function drawBursts() {
        maybeSpawnBurst();
        for (let i = bursts.length - 1; i >= 0; i--) {
            const b = bursts[i];
            b.r += 3.5;
            b.alpha *= 0.96;

            ctx.strokeStyle = `rgba(0, 255, 136, ${b.alpha})`;
            ctx.lineWidth = 1.5;
            ctx.beginPath();
            ctx.arc(b.x, b.y, b.r, 0, Math.PI * 2);
            ctx.stroke();

            // Inner glow ring
            ctx.strokeStyle = `rgba(180, 255, 220, ${b.alpha * 0.5})`;
            ctx.lineWidth = 0.8;
            ctx.beginPath();
            ctx.arc(b.x, b.y, b.r * 0.6, 0, Math.PI * 2);
            ctx.stroke();

            if (b.r > b.max || b.alpha < 0.05) {
                bursts.splice(i, 1);
            }
        }
    }

    // ---------- Layer D: Scanline ----------
    let scanY = 0;
    function drawScanline() {
        const grad = ctx.createLinearGradient(0, scanY - 40, 0, scanY + 4);
        grad.addColorStop(0, 'rgba(0, 255, 136, 0)');
        grad.addColorStop(0.85, 'rgba(0, 255, 136, 0.05)');
        grad.addColorStop(1, 'rgba(0, 255, 136, 0.35)');
        ctx.fillStyle = grad;
        ctx.fillRect(0, scanY - 40, W, 44);

        scanY += 1.6;
        if (scanY > H + 60) scanY = -60;
    }

    // ---------- Main loop ----------
    function resize() {
        W = canvas.width  = window.innerWidth;
        H = canvas.height = window.innerHeight;
        initRain();
        initParticles();
        scanY = 0;
    }
    window.addEventListener('resize', resize);
    resize();

    function frame() {
        // Full fade for rain trails
        ctx.fillStyle = 'rgba(0, 4, 2, 0.16)';
        ctx.fillRect(0, 0, W, H);

        drawParticles();
        drawRain();
        drawBursts();
        drawScanline();

        requestAnimationFrame(frame);
    }
    frame();
})();

/* =========================================================
   Floating scroll buttons
   ========================================================= */
(function scrollButtons() {
    const topBtn = document.getElementById('scroll-top-btn');
    const bottomBtn = document.getElementById('scroll-bottom-btn');
    if (!topBtn || !bottomBtn) return;

    // Show top button only after scrolling down
    function toggleTopButton() {
        if (window.scrollY > 300) {
            topBtn.classList.add('visible');
        } else {
            topBtn.classList.remove('visible');
        }
    }
    window.addEventListener('scroll', toggleTopButton, { passive: true });
    toggleTopButton();

    topBtn.addEventListener('click', () => {
        window.scrollTo({ top: 0, behavior: 'smooth' });
    });

    bottomBtn.addEventListener('click', () => {
        window.scrollTo({
            top: document.documentElement.scrollHeight,
            behavior: 'smooth'
        });
    });
})();
