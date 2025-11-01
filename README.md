<h1>🧾 Janji</h1>
Saya Muhammad Maulana Adrian dengan NIM 2408647 mengerjakan Tugas Praktikum 6
dalam mata kuliah Desain Pemrograman Berbasis Objek untuk keberkahanNya maka
saya tidak melakukan kecurangan seperti yang telah dispesifikasikan. Aamiin

<h2>🐦 Flappy Bird - Java Swing Implementation</h2>
Implementasi sederhana dari game Flappy Bird yang dibuat menggunakan Java dan library Swing untuk antarmuka grafis (GUI).

<h2>🎮 Tampilan GUI Program</h2>
Tampilan antarmuka aplikasi Flappy Bird:<br>

<img width="344" height="631" alt="image" src="https://github.com/user-attachments/assets/0eba8ee9-d661-47a4-8e2d-323b36fa21cc" />

<h2>🧩 Desain Program</h2>

* App.java: Class utama untuk menjalankan aplikasi dan menampilkan MainMenu.<br>
* MainMenu.java: Mengatur tampilan awal ketika program dijalankan.<br>
* Logic.java: Berisi semua logika game (pergerakan, penempatan pipa, deteksi tabrakan, game over, dan penanganan input keyboard).<br>
* View.java: Bertanggung jawab untuk menggambar semua elemen game (burung, pipa, tanah, skor, pesan Game Over) di layar.<br>
* Player.java: Merepresentasikan objek burung dengan atribut posisi ($\text{posX}$, $\text{posY}$), dimensi, gambar, dan kecepatan vertikal ($\text{velocityY}$).<br>
* Pipe.java: Merepresentasikan objek pipa dengan atribut posisi ($\text{posX}$, $\text{posY}$), dimensi, gambar, dan status $\text{passed}$ (sudah dilewati untuk perhitungan skor).<br>

<h2>🚀 Fitur Utama</h2>

* Logika Game: Mengimplementasikan fisika dasar seperti gravitasi untuk gerakan jatuh burung.<br>
* Kontrol: Pemain dapat membuat burung melompat menggunakan tombol SPACE.<br>
* Pipa Bergerak: Pipa dihasilkan secara berkala dan bergerak dari kanan ke kiri.<br>
* Deteksi Tabrakan (Collision): Game berakhir jika burung menabrak pipa atau menyentuh tanah.<br>
* Sistem Skor: Skor bertambah setiap kali burung berhasil melewati sepasang pipa.<br>
* Menu Utama: Tampilan menu awal dengan opsi Play Game dan Exit.<br>
* Restart dan Exit: Setelah Game Over, pemain dapat Restart game dengan tombol R atau Exit program dengan tombol E.<br>


<h2>🧭 Penjelasan Alur Program Flappy Bird (Java Swing)</h2>
    <ol>
        <li>
            <strong>Inisialisasi Aplikasi</strong>
            <ul>
                <li><code>App.java</code> menjalankan tampilan utama, yaitu <code>MainMenu.java</code>.</li>
            </ul>
        </li>
        <li>
            <strong>Menu Utama</strong>
            <ul>
                <li>Tampilan awal menyajikan judul game "Flappy Bird", tombol "PLAY GAME", dan tombol "EXIT".</li>
                <li>Pilihan "EXIT" akan keluar dari program.</li>
                <li>Pilihan "PLAY GAME" akan menutup menu dan memulai permainan dengan memanggil <code>startGame()</code>.</li>
            </ul>
        </li>
        <li>
            <strong>Memulai Game (<code>startGame()</code>)</strong>
            <ul>
                <li><code>startGame()</code> membuat objek <code>Logic</code> (logika game) dan <code>View</code> (tampilan), lalu menghubungkannya.</li>
                <li><strong>Timer <code>gameLoop</code></strong> dimulai (60 FPS) untuk menggerakkan dan memperbarui fisika game secara konstan.</li>
                <li><strong>Timer <code>pipesCooldown</code></strong> dimulai (setiap 1.5 detik) untuk memanggil <code>placePipes()</code>, yang berfungsi untuk menghasilkan pasangan pipa baru secara berkala.</li>
            </ul>
        </li>
        <li>
            <strong>Loop Game dan Fisika (<code>Logic.actionPerformed</code>)</strong>
            <p>Metode ini dieksekusi di setiap siklus <code>gameLoop</code> untuk memperbarui state game:</p>
            <ul>
                <li><strong>Pergerakan Pemain (<code>move()</code>):</strong>
                    <ul>
                        <li>Menambahkan **gravitasi** ke <code>velocityY</code> pemain dan memperbarui <code>posY</code> pemain.</li>
                        <li>Memastikan burung tidak terbang melewati batas atas *frame*.</li>
                    </ul>
                </li>
                <li><strong>Pergerakan Pipa:</strong>
                    <ul>
                        <li>Menggerakkan semua pipa ke kiri (mengurangi <code>posX</code> pipa).</li>
                    </ul>
                </li>
                <li><strong>Deteksi dan Skor:</strong>
                    <ul>
                        <li><strong>Tabrakan dengan Pipa:</strong> Setiap pipa dicek tabrakan dengan burung (<code>collision()</code>). Jika terjadi tabrakan, variabel <strong><code>gameOver</code></strong> diatur menjadi <code>true</code>.</li>
                        <li><strong>Penghitungan Skor:</strong> Jika pipa atas telah melewati posisi <code>posX</code> burung dan status <code>passed</code> masih <code>false</code>, skor bertambah, dan <code>passed</code> diatur menjadi <code>true</code> untuk pasangan pipa tersebut.</li>
                    </ul>
                </li>
                <li><strong>Deteksi Jatuh (Game Over):</strong>
                    <ul>
                        <li>Cek tabrakan dengan **dasar *frame*** (batas bawah tinggi 640). Jika burung menyentuh batas ini, <code>gameOver</code> menjadi <code>true</code>.</li>
                    </ul>
                </li>
                <li><strong>Penghentian:</strong> Jika <code>gameOver</code> bernilai <code>true</code>, kedua timer (<code>gameLoop</code> dan <code>pipesCooldown</code>) dihentikan.</li>
                <li><strong>Rendering:</strong> <code>View.repaint()</code> dipanggil untuk menggambar ulang layar.</li>
            </ul>
        </li>
        <li>
            <strong>Penggambaran (<code>View.draw</code>)</strong>
            <ul>
                <li>Metode ini menggambar ulang seluruh tampilan: *background*, pipa, dan objek pemain (burung) di posisi terbarunya.</li>
                <li>Jika <code>gameOver</code>, maka akan ditampilkan pesan **"GAME OVER"**, skor akhir, dan instruksi selanjutnya: "Press R to restart" dan "Press E to exit".</li>
            </ul>
        </li>
        <li>
            <strong>Input Pemain (<code>Logic.keyPressed</code>)</strong>
            <ul>
                <li>Menekan tombol <strong>Spasi (SPACE)</strong> akan membuat burung melompat, hanya jika <code>gameOver</code> adalah <code>false</code>.</li>
                <li>Menekan tombol <strong>R</strong> saat <code>gameOver</code> akan me-restart permainan.</li>
                <li>Menekan tombol <strong>E</strong> saat <code>gameOver</code> akan keluar dari program (exit).</li>
            </ul>
        </li>
    </ol>

<h2>⚙️ Proses berjalan</h2>

https://github.com/user-attachments/assets/7db1cafe-0d55-47da-8ffb-635534be8ec3
