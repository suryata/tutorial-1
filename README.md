[![SonarCloud](https://sonarcloud.io/images/project_badges/sonarcloud-white.svg)](https://sonarcloud.io/summary/new_code?id=suryata_tutorial-1)<br/>

**Tutorial Pemrograman Lanjut 2023/2024 Genap**
>Link Aplikasi
### [E-Shop](https://eshop-suryata.koyeb.app/)

**I Made Surya Anahata Putra**<br/>
**2206081370**<br/>
**ProLan B**<br/>
# **Modul 2 - Advanced Programming**
> List the code quality issue(s) that you fixed during the exercise and explain your strategy on fixing them
#### 1. **Table should have caption**
**Permasalahan:** table perlu mempunyai sebuah caption untuk mengidentifikasi table tersebut.
**Contoh Isu pada Proyek**:
```html
<table border="1" class="table table-striped table-responsive-md" id="productList">
        <thead>
        <tr>
            <th scope="col">Product Name</th>
            <th scope="col">Quantity</th>
            <th scope="col">Actions</th>
        </tr>
        </thead>
        <tbody>
        <tr th:each="product: ${products}">
            <td th:text="${product.productName}"></td>
            <td th:text="${product.productQuantity}"></td>
            <td>
                <a th:href="@{/product/delete/{productId}(productId=${product.productID})}" class="btn btn-danger btn-sm">Delete</a>
                <a th:href="@{/product/edit/{productId}(productId=${product.productID})}" class="btn btn-info btn-sm">Edit</a>
            </td>            
        </tr>
        </tbody>
    </table>
```
**Solusi:** Menambahkan tag `<caption>` pada tabel tersebut.
```html
<table border="1" class="table table-striped table-responsive-md" id="productList">
        <caption>Product table</caption>
        <thead>
        <tr>
            <th scope="col">Product Name</th>
            <th scope="col">Quantity</th>
            <th scope="col">Actions</th>
        </tr>
        </thead>
        <tbody>
        <tr th:each="product: ${products}">
            <td th:text="${product.productName}"></td>
            <td th:text="${product.productQuantity}"></td>
            <td>
                <a th:href="@{/product/delete/{productId}(productId=${product.productID})}" class="btn btn-danger btn-sm">Delete</a>
                <a th:href="@{/product/edit/{productId}(productId=${product.productID})}" class="btn btn-info btn-sm">Edit</a>
            </td>            
        </tr>
        </tbody>
    </table>
```

>Look at your CI/CD workflows (GitHub)/pipelines (GitLab). Do you think the current implementation has met the definition of Continuous Integration and Continuous Deployment?<br>

Pada workflows CI/CD yang sudah saya implementasikan pada proyek tutorial-1, saya sudah mengimplementasikan CI/CD pada proyek saya yaitu dengan menggunakan _GitHub Actions_ untuk menjalankan _workflow_ yang saya buat. <br>
Untuk workflow yang saya gunakan yaitu:
-  `ci.yml`
-  `scorecard.yml`
-  `sonarcloud.yml`
-  `pmd.yml`<br>
<br>
_Workflow-workflow_ ini secara otomatis akan dijalankan ketika ada _push_ atau _pull request_ ke suatu _branch_. Ini merupakan suatu pengaplikasian dari konsep continuous integration (CI). <br>
Untuk Continuous Deployment (CD) sendiri , saya menggunakan paltform yang direkomendasikan pada modu lyaitu _Koyeb_ sebagai _platform_ yang akan secara otomatis melakukan _deploy_ aplikasi saya ketika ada _push_ atau _pull request_ ke suatu _branch_.

## Reflection sebelumnya
<details>
<summary>Module 1</summary>

# **Modul 1 - Advanced Programming**
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




