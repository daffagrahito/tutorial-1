# ADV Shop

> #### Muhammad Daffa Grahito Triharsanto - 2206820075 - Pemrograman Lanjut B

## Module 2 - Pemrograman Lanjut 2023/2024 Genap

### Reflection ✏️
![Reflection Module 2](https://cdn.discordapp.com/attachments/1201794782402187324/1206840567573651466/image.png?ex=65dd78dd&is=65cb03dd&hm=1a3604919e4f78b6a9805c09ff2ed67d1229e2997a95b2d31108e138576dab2d&)
![Reflection Module 2](https://cdn.discordapp.com/attachments/1201794782402187324/1206840664642293770/image.png?ex=65dd78f4&is=65cb03f4&hm=0f72c6c1e008c2a3bfe493a0e3f4098bcb2f15b29fb8166a492c0f8ac929bfb1&)

1. Beberapa *code quality issue(s)* yang saya benarkan dalam exercise ini diantaranya adalah:
  
**Fields in interfaces and annotations are automatically `public static final`, and methods are `public abstract`.**
  
- Ini terjadi karena sebelumnya method-method pada interface `ProductService` memiliki modifier `public` di setiap methodnya. Padahal setiap method di interface sudah otomatis merupakan `public static final` tanpa perlu *declare* modifiernya. Untuk itu menghapus modifier public membenarkan permasalahan ini.

**Reports import statements that can be removed. They are either unused, duplicated, or the members they import are already implicitly in scope, because they're in `java.lang`, or the current package.** 
- Ini terjadi karena memang terdapat `import 'org.springframework.web.bind.annotation.*'` di `ProductController.java:8` yang mengimport module `annotation` lain yang tidak diperlukan juga, sehingga hanya perlu mengimport seperlunya saja. Untuk itu saya *manually* menambahkan satu-satu import yang saya pakai di ProductController sehingga menjadi seperti ini:
```java
...
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
...
``` 
**Add a description to this table. `"<table>"` tags should have a description, `src/main/resources/templates/ListProduct.html`**
- Perlu menambahkan caption di table html untuk menambah code reliability, untuk itu menambahkan `<caption></caption>` menyelesaikan permasalahan ini.

2. Menurut saya, saya sudah cukup mengimplementasikan CI/CD di project ini. Untuk *Continuous Integration* (CI), saya menggunakan GitHub Actions untuk menjalankan workflows yang saya define sebelumnya seperti `ci.yml`, `pmd.yml`, `sonarcloud.yml`, dan `scorecard.yml`. Workflows tersebut otomatis dijalankan apabila terdapat push/pull request ke suatu branch. Selain itu saya juga mengimplementasikan *Continuous Deployment* (CD) dengan menggunakan Koyeb sebagai *PaaS*nya. Sama seperti CI, apabila terdapat push/pull request ke suatu branch maka akan secara otomatis ter-*deploy* di Koyeb. Dengan begini saya dapat me-*maintain* code saya dengan lebih baik setiap kali saya ingin melakukan perubahan code dalam project saya agar dapat mendeteksi error lebih awal.

## Module Sebelumnya 📑

<details>
<summary> <b> Reflection Module 1 </b> </summary>

## Module 1 - Pemrograman Lanjut 2023/2024 Genap

### Reflection 1 ✏️

![Soal Reflection 1](https://cdn.discordapp.com/attachments/711462986874617956/1204267427412836362/image.png?ex=65d41c70&is=65c1a770&hm=0d73a9cbc30506d48f0c035feb474344c77bb9a4e056c77b4ac93fe96642b358&)

Setelah me-*review* kembali source code yang telah saya buat untuk setiap kedua fitur baru yaitu ***edit*** dan **delete** product, saya rasa saya sudah cukup baik mengimplementasikan **clean code principle** dan **secure coding**. Namun saya pikir terdapat beberapa improvisasi yang bisa dilakukan, yaitu validasi input. Karena di modul tidak di ingatkan untuk melakukan validasi input, maka dari itu saya membuat validasi input dengan menggunakan html validation dan Exception di Java. Semisal sebelumnya (tanpa input handling sama sekali seperti di modul) saya memasukkan input nama produk yang empty dan quantity < 0, secara logika hal tersebut seharusnya tidak sesuai sebagaimana harusnya untuk input. Maka dari itu saya menambahkan input validation pada template `html`: `CreateProduct.html` dan `EditProduct.html`, dan juga `throws Exception` pada setter jika mengeset dengan input yang tidak valid agak dan agar tidak terlalu *vulnarable*.

### Reflection 2 ✏️

![Soal Reflection 2](https://cdn.discordapp.com/attachments/711462986874617956/1204291942301564988/image.png?ex=65d43345&is=65c1be45&hm=41140d5fff6ebd371e266b8cf1679a696320123b37a36bb89ee8c84eeadad634&)

1. Saya rasa tidak ada jumlah yang tepat untuk seberapa banyak *unit test* pada sebuah class. Tapi harusnya minimal ada satu untuk setiap *method* di suatu class, namun jumlahnya bervariasi tergantung class yang kita desain. Beberapa *unit test* mungkin diperlukan untuk sebuah method agar mencakup beberapa *case scenario* dan *edge cases*. Untuk memastikan apakah unit test kita cukup untuk *verify* program kita, salah satu cara untuk memastikannya adalah dengan ***code coverage***.
     
    <h4> Code Coverage </h4>

    *Code coverage* adalah alat ukur yang digunakan untuk mengukur sejauh mana kode program telah dieksekusi oleh rangkaian test. Tujuan dari *code coverage* adalah untuk mengetahui seberapa baik kode kita telah diuji dan seberapa banyak bagian dari kode yang belum diuji. Untuk memastikan apakah unit test kita cukup untuk *verify* program kita, kita dapat menargetkan *code coverage* setinggi-tingginya hingga 100%. Mencapai 100% code coverage berarti setiap baris kode kita dieksekusi selama testing. Namun, *code coverage* yang tinggi hingga 100% bukan berarti kode kita *bug-free*. Contohnya yaitu test kita mungkin tidak meng-*cover* semua kemungkinan kombinasi input, atau mungkin ada *logical errors* pada kode kita yang tidak dicakup oleh test. 

    ```Java
    public class Calculator {
        public int divide(int a, int b) {
            return a / b;
        }
    }

    public class CalculatorTest {
        @Test
        public void testDivide() {
            Calculator calculator = new Calculator();
            assertEquals(2, calculator.divide(4, 2));
        }
    }
    ```
    Dalam contoh ini, class `CalculatorTest` memiliki 100% *code coverage* karena menguji satu-satunya method dalam class `Calculator`. Namun, method `divide` memiliki bug: ia tidak menangani kasus di mana b adalah 0, yang akan menyebabkan `ArithmeticException` dilemparkan. Bug ini tidak akan terdeteksi oleh pengujian yang ada, menunjukkan bahwa 100% *code coverage* tidak menjamin bahwa kode tersebut bebas dari bug.

2. Semisal dibuat functional test suite baru sebagai sebuah Java class baru, akan ada beberapa hal yang membuat test suite kita tidak mencakup *clean code*. Salah satunya mungkin yaitu duplikasi class dan file. Jika setup prosedur dan variabel instance yang sama disalin secara langsung dari kelas tes fungsional sebelumnya, itu akan menciptakan duplikasi kode yang tidak efisien. Ini dapat membuat kode sulit dipelihara dan meningkatkan risiko kesalahan jika perubahan perlu dilakukan di masa depan. Alternatif yang lebih mungkin daripada menyalin setup prosedur dari kelas tes fungsional sebelumnya adalah mempertimbangkan untuk menyatukan setup ke dalam suatu method helper yang dapat dipanggil dari semua tes fungsional yang memerlukannya dan juga kita bisa mengkapsulasi setup prosedur dan variabel instance dalam method atau helper class terpisah untuk menghindari duplikasi kode.

</details>
