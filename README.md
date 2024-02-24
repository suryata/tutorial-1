# **Tutorial Pemrograman Lanjut 2023/2024 Genap**
[![Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=suryata_tutorial-1&metric=vulnerabilities)](https://sonarcloud.io/summary/new_code?id=suryata_tutorial-1)
[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=suryata_tutorial-1&metric=bugs)](https://sonarcloud.io/summary/new_code?id=suryata_tutorial-1)
[![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=suryata_tutorial-1&metric=security_rating)](https://sonarcloud.io/summary/new_code?id=suryata_tutorial-1)
[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=suryata_tutorial-1&metric=sqale_rating)](https://sonarcloud.io/summary/new_code?id=suryata_tutorial-1)
[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=suryata_tutorial-1&metric=code_smells)](https://sonarcloud.io/summary/new_code?id=suryata_tutorial-1)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=suryata_tutorial-1&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=suryata_tutorial-1)

>[E-Shop](https://eshop-suryata.koyeb.app/)

**I Made Surya Anahata Putra**<br/>
**2206081370**<br/>
**ProLan B**<br/>

# **Modul 3 - Advanced Programming**
## 1. Explain what principles you apply to your project! ##

###     1. *Single Responsibility Principle (SRP)*
Prinsip SRP yang saya terapkan pada project ini pada bagian Repository adalah dengan memisahkan method generate UUID dengan membuat class baru yaitu "UniqueIdGenerator" yang berfungsi untuk membuat UUID random. Ini sesuai dengan prinsip SRP yaitu dengan membuat setiap class hanya bertanggung jawab untuk menjalankan satu method saja.
```java
UniqueIdGenerator.java:
import java.util.UUID;

public class UniqueIdGenerator {
    public static String generate() {
        return UUID.randomUUID().toString();
    }
}
```
Selain itu saya juga mengaplikasikan SRP pada Controller yaitu dengan memisahkan controller berdasarkan entitas yang mereka kelola, saya mengurangi kompleksitas dan ketergantungan antar bagian dari aplikasi. Misalnya, jika ada perubahan dalam cara entitas Product yang harus diproses atau ditampilkan, hanya ProductController yang perlu diubah, tanpa mempengaruhi CarController atau bagian lain dari aplikasi. Ini mendukung prinsip pemeliharaan dan skalabilitas kode yang baik.

### 2. *Open-Closed Principle (OCP)*
Pada project ini saya menggunakan prinsip OCP untuk membuat interface baru yang digunakan untuk CarRepository dan ProductRepository karena memiliki kesamaan yaitu sebagai suatu penyimpanan objek yang dibuat, maka saya membuat suatu interface bernama "GenericRepository" yang berguna untuk pengembangan selanjutnya yang sesuai dengan prinsip OCP.
Berikut merupakan interface dari `GenericRepository`:
```java
public interface GenericRepository<T> {
    T create(T entity);
    Iterator<T> findAll();
    T findById(String id);
    T update(String id, T entity);
    void delete(String id);
}
```

### 3. *Liskov Substitution Principle (LSP)*
Dalam mengaplikasikan LSP saya menghapus interface carService dan productService karena memiliki method yang mirip dan saya membuat suatu interface baru yaitu "GenericService" yaitu sebuah interface umum dengan generics memungkinkan objek dari berbagai kelas yang mengimplementasikan interface ini untuk digunakan secara bergantian tanpa merusak integritas aplikasi.
Berikut merupakan penerapan dari interface tersebut:
```java
public interface GenericService<T> {
    T create(T entity);
    List<T> findAll();
    T update(String id, T entity);
    T findById(String id);
    void delete(String id);
}
```
### 4. *Interface Segregation Principle (ISP)*
Pada Level Controller
Dalam desain yang telah disampaikan, penerapan ISP tercermin melalui pembuatan ProductController dan CarController secara terpisah.

ProductController hanya meminta operasi yang berkaitan dengan Product dari ProductService, dan sama sekali tidak bergantung pada operasi atau fungsi yang berkaitan dengan Car.
CarController beroperasi secara analog, hanya berinteraksi dengan CarService dan tidak bergantung pada fungsi yang berkaitan dengan Product.
Ini memastikan bahwa setiap controller hanya bergantung pada interface layanan yang menyediakan fungsi yang relevan dengan entitas yang mereka kelola, sesuai dengan ISP.

### 5. *Dependency Inversions Principle (DIP)*
Pengaplikasian dalam Controller:
Prinsip Dependency Inversion Principle (DIP) diaplikasikan dalam ProductController dan CarController melalui penggunaan injeksi dependensi (@Autowired) pada konstruktor. Prinsip ini menyatakan dua hal utama:

- Modul tingkat tinggi tidak harus bergantung pada modul tingkat rendah. Kedua harus bergantung pada abstraksi.
- Abstraksi tidak boleh bergantung pada detail. Detail harus bergantung pada abstraksi.

Dalam konteks controller ini, modul tingkat tinggi (controller) tidak langsung bergantung pada implementasi layanan (modul tingkat rendah) seperti ProductServiceImpl atau CarServiceImpl. Sebaliknya, mereka bergantung pada abstraksi (GenericService<T> interfaces).

## 2. Explain the advantages of applying SOLID principles to your project with examples ##
Berikut adalah beberapa contoh spesifik dari keuntungan penerapan SOLID dalam proyek saya:

### 1. Single Responsibility Principle (SRP) ### 
Dengan memisahkan tanggung jawab, seperti menggunakan UniqueIdGenerator untuk pembuatan UUID, dan memisahkan ProductController dari CarController, saya membuat komponen yang lebih bersih dan terfokus. Ini memudahkan pemeliharaan dan pengujian karena perubahan pada satu bagian tidak mempengaruhi bagian lain. Misalnya, jika perlu mengubah cara UUID dihasilkan, saya hanya perlu memodifikasi UniqueIdGenerator, tanpa menyentuh bagian lain dari kode.

### 2. Open-Closed Principle (OCP) ### 
Dengan mendesain GenericRepository yang dapat diperluas untuk tipe data baru tanpa perlu mengubah kode yang ada, saya memastikan bahwa sistem saya mudah untuk dikembangkan. Ketika ada kebutuhan untuk menambahkan entitas baru, seperti Customer, saya hanya perlu membuat implementasi baru dari GenericRepository tanpa mengubah definisi atau implementasi yang sudah ada.

### 3. Liskov Substitution Principle (LSP) ### 
Implementasi GenericService memungkinkan objek dari berbagai kelas yang mengimplementasikan interface ini digunakan secara bergantian. Hal ini meningkatkan modularitas kode dan memudahkan penggantian atau penambahan fungsionalitas baru tanpa merusak sistem. Sebagai contoh, jika saya memutuskan untuk mengganti logika bisnis untuk Product, selama mengikuti kontrak yang ditetapkan oleh GenericService, penggantian tersebut tidak akan merusak fungsi yang bergantung pada ProductService.

### 4. Interface Segregation Principle (ISP) ### 
Dengan memisahkan interface (ProductController dan CarController, serta ProductRepository dan CarRepository), setiap komponen hanya bergantung pada fungsionalitas yang mereka perlukan. Ini mengurangi ketergantungan tidak perlu dan membuat kode lebih bersih serta mudah untuk diikuti. Misalnya, pengembang yang bekerja pada Car tidak perlu memahami atau bahkan mengetahui tentang kode atau fungsionalitas yang spesifik untuk Product.

### 5. Dependency Inversion Principle (DIP) ### 
Injeksi dependensi melalui konstruktor (@Autowired) dalam controller memastikan bahwa komponen tingkat tinggi tidak bergantung pada komponen tingkat rendah, tetapi keduanya bergantung pada abstraksi. Ini memudahkan pengujian unit dan dekoplasi kode, membuat sistem lebih fleksibel dan mudah untuk diadaptasi atau dikembangkan.

## 3. Explain the disadvantages of not applying SOLID principles to your project with examples ##
Tidak menerapkan prinsip SOLID dapat mengakibatkan berbagai masalah dalam pengembangan dan pemeliharaan perangkat lunak, termasuk:

### 1. Kurangnya Modularitas ###
Tanpa SRP, kode cenderung menjadi monolitik dan melakukan terlalu banyak fungsi. Sebagai contoh, jika ProductController juga menangani logika untuk Car, perubahan pada entitas Car bisa tidak sengaja mempengaruhi fungsi yang berkaitan dengan Product.

### 2. Kesulitan dalam Pengembangan dan Pemeliharaan ###
Tidak mengikuti OCP membuat kode saya sulit untuk diperluas. Misalnya, menambahkan entitas baru ke sistem yang tidak menggunakan GenericRepository mungkin memerlukan modifikasi kode yang ada, meningkatkan risiko bug.

### 3. Masalah Penggantian Komponen ### 
Tidak adanya LSP membuat penggantian komponen dengan implementasi baru berisiko merusak sistem. Misalnya, mengganti ProductService dengan versi baru yang tidak kompatibel dengan penggunaan sebelumnya bisa menyebabkan kesalahan runtime.

### 4. Ketergantungan pada Modul yang Tidak Dierlukan ### 
Tanpa ISP, sistem mungkin memaksa komponen untuk bergantung pada fungsionalitas yang mereka tidak butuhkan, membuat sistem menjadi lebih rumit dan sulit untuk dipahami. Sebagai contoh, jika CarController harus menggunakan bagian dari ProductService, ini akan menciptakan ketergantungan yang tidak perlu.

### 5. Tingkat Ketergantungan yang tinggi ###
Tidak menerapkan DIP membuat proyek saya mungkin memiliki ketergantungan yang tinggi antara komponen tingkat rendah, yang membuat pengujian dan pengembangan lebih sulit. ketergantungan yang tinggi antara ProductController dan implementasi spesifik dari ProductService dapat mempersulit pengujian unit atau penggantian service.

## Reflection sebelumnya
<details>
<summary>Module 2</summary>


# **Modul 2 - Advanced Programming**
> List the code quality issue(s) that you fixed during the exercise and explain your strategy on fixing them
#### 1. **Table should have caption**
**Permasalahan:** table perlu mempunyai sebuah caption untuk mengidentifikasi table tersebut.<br/>
**Isu pada Proyek**:
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

#### 2. **Remove this unused productID, productName, productQuantity private field.**
**Isu pada Proyek**:
```java
public class Product {
     private String productID;
     private String productName;
     private int productQuantity;
}
```
**Solusi:** Menghapus field private pada model tersebut
```java
public class Product {
     String productID;
     String productName;
     int productQuantity;
}
```
>Look at your CI/CD workflows (GitHub)/pipelines (GitLab). Do you think the current implementation has met the definition of Continuous Integration and Continuous Deployment?<br>

Pada workflows CI/CD yang sudah saya implementasikan pada proyek tutorial-1, saya sudah mengimplementasikan CI/CD pada proyek saya yaitu dengan menggunakan _GitHub Actions_ untuk menjalankan _workflow_ yang saya buat. <br>
Untuk workflow yang saya gunakan yaitu:
-  `ci.yml`
-  `scorecard.yml`
-  `sonarcloud.yml`
-  `pmd.yml`<br>

Workflow-workflow ini secara otomatis akan dijalankan ketika ada push atau pull request ke suatu _branch_. Ini merupakan suatu pengaplikasian dari konsep continuous integration (CI). <br>
Untuk Continuous Deployment (CD) sendiri, saya menggunakan paltform yang direkomendasikan pada modul yang diberikan yaitu dengan menggunakan platform _Koyeb_ sebagai _platform_ yang akan secara otomatis melakukan _deploy_ aplikasi ketika ada _push_ atau _pull request_ ke suatu _branch_, ini dilengkapi juga dengan _Dockerfile_ yang diberikan pada modul. 
</details>

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
</details>