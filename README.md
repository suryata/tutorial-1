# **Exercise 1 - Advanced Programming**
[![SonarCloud](https://sonarcloud.io/images/project_badges/sonarcloud-white.svg)](https://sonarcloud.io/summary/new_code?id=suryata_tutorial-1)
**I Made Surya Anahata Putra**<br/>
**2206081370**<br/>
**ProLan B**<br/>

## **Prinsip Clean Code yang Diterapkan**

**1. Nama yang Berarti (Meaningful Names)**<br/>
Variabel, metode, dan kelas diberi nama dengan cara yang jelas mencerminkan tujuan mereka, meningkatkan kemudahan bacaan dan kemudahan perawatan. Hal ini seperti productID, productName, dan productQuantity yang menunjukan nama variabel itu mencerminkan tujuan mereka.

**2. Menjaga Fungsi tetap Kecil**<br/>
Metode(method) dijaga agar tetap kecil dan fokus. Setiap metode mencapai satu tugas, yang membuat kode lebih mudah diuji dan lebih mudah dipahami. Hal ini ditunjukkan pada fungsi create, edit, dan delete yang hanya melakukan satu tujuan saja.

**3. DRY (Don't Repeat Yourself)**<br/>
Saya meminimalkan duplikasi dengan mengabstraksi fungsionalitas umum menjadi metode dan kelas yang dapat digunakan kembali, memastikan bahwa setiap bagian method memiliki representasi tunggal dan tidak ambigu dalam sistem.

## **Area untuk Peningkatan**
Setelah meninjau kode sumber, area berikut ini telah diidentifikasi untuk perbaikan:
<br/><br/>
**1. Validasi Input**<br/>
Saat ini, validasi input untuk quantity pada saat create dan edit product belum ada, sehingga masih menimbulkan error jika kita memasukkan tipe selain int.

**2. Penambahan UUID**<br/>
Pada awalnya, productID tidak berisi value ketika dibuat (bernilai null), saya menambahkan autogenerate UUID sebagai value untuk productID untuk memudahkan operasi delete dan edit.

# **Exercise 2 - Advanced Programming**

## **Refleksi Pengujian Fungsional dan Unit Test**
Menulis unit test memberikan perasaan percaya diri bahwa kode yang ditulis bekerja sesuai dengan yang diharapkan. Unit test membantu mengidentifikasi kesalahan pada tahap awal dan memudahkan proses debugging. Jumlah unit test dalam satu kelas bisa bervariasi tergantung pada kompleksitas dan fungsi dari kelas tersebut. Penting untuk mencakup skenario yang berbeda, termasuk kasus uji positif dan negatif.

Meskipun unit test sangat penting, code coverage 100% tidak selalu menjamin bahwa kode bebas dari bug atau kesalahan. Coverage tinggi bisa menunjukkan bahwa sebagian besar kode telah diuji, tetapi tidak selalu mencakup semua skenario penggunaan atau perilaku edge case. Oleh karena itu, code coverage harus digunakan sebagai alat untuk meningkatkan kualitas kode, bukan sebagai indikator tunggal keberhasilan.

Ketika membuat suite test fungsional tambahan seperti yang diminta, penting untuk mempertimbangkan prinsip-prinsip clean code untuk menjaga kualitas kode:

> Potensi Masalah Clean Code:<br/>
- Duplikasi Kode<br/>
Menggunakan prosedur setup dan variabel instan yang sama dapat menyebabkan duplikasi kode.
- Ketergantungan yang Tinggi<br/>
Suite test yang serupa mungkin bergantung pada setup yang sama, membuatnya sulit untuk diubah atau disesuaikan nantinya.
- Kesulitan Pemeliharaan<br/>
Duplikasi kode dan ketergantungan yang tinggi membuat pemeliharaan menjadi lebih sulit.
>Saran untuk Peningkatan:<br/>
- Refactoring ke Metode yang Dapat Digunakan Kembali<br/>
jika setup untuk suite test serupa, pertimbangkan untuk memindahkannya ke metode yang dapat digunakan kembali atau kelas basis test.
- Penggunaan Inheritance atau Composition<br/>
Gunakan pewarisan atau komposisi untuk meminimalkan duplikasi kode dan memanfaatkan kembali setup yang umum.
- Pembuatan Abstraksi yang Tepat<br/>
Buat abstraksi untuk operasi umum seperti setup dan teardown untuk meningkatkan modularity dan maintainability.
