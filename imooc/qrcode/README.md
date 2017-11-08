# qrcode

## Runtime Environment
- [Java 7](http://www.oracle.com/technetwork/java/javase/downloads/jdk7-downloads-1880260.html)

## 1.zxing
- [zxing](https://github.com/zxing/zxing)
- CreateQRCode `qrcode\src\main\java\com\evangel\zxing\CreateQRCode.java`
- ReadQRCode `qrcode\src\main\java\com\evangel\zxing\ReadQRCode.java`

## 2.qrcode
- 生成：[qr_java](http://www.swetake.com/qrcode/java/qr_java.html)
- 读取：[Open Source QR Code Library](https://zh.osdn.net/projects/qrcode/)
- CreateQRCode `qrcode\src\main\java\com\evangel\qrcode\CreateQRCode.java`
- ReadQRCode `qrcode\src\main\java\com\evangel\qrcode\ReadQRCode.java`

## 3.jquery-qrcode
- [jquery-qrcode](https://github.com/jeromeetienne/jquery-qrcode)
- http://localhost:8080/qrcode.jsp

## Changes

### zxing with a logo
- added `qrcode\src\main\java\com\evangel\zxing\util`
- TestQrcodeUtils `qrcode\src\main\java\com\evangel\zxing\util\TestQrcodeUtils.java`

### Result

![zxing with a logo](http://s1.wailian.download/2017/11/08/img-logo-new.jpg)

## 2-Dimensional Barcodes

### 二维码的分类：
- 线性堆叠式二维码
- 矩阵式二维码
- 邮政码

### 目前流行的三大国际标准
* PDF417：不支持中文
* DM：专利未公开，需支付专利费用
* QR code：专利公开，支持中文
    * Quick Response Code
    * QR code比其他二维码相比，具有识读速度快，数据密度大，占用空间小的优势。
    * QR Code是有日本Demso公司于1994年研制的一种矩阵二维码符号码

### 纠错能力：
 * L级：约可纠错7%的数据码字
 * M级：约可纠错15%的数据码字
 * Q级：约可纠错25%的数据码字
 * H级：约可纠错30%的数据码字
 * 纠错能力越高，存储的数据就越少

## Links
- [Java生成二维码](http://www.imooc.com/learn/531)
- [二维码工具类 - QrcodeUtils.java](http://www.cnblogs.com/zhoubang521/p/5200118.html)