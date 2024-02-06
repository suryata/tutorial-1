# **Exercise 1 - Advanced Programming**
**I Made Surya Anahata Putra**<br/>
**2206081370**<br/>
**ProLan B**<br/>

## **Prinsip Kode Bersih yang Diterapkan**

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

