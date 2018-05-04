# easyPR

## Runtime Environment
 - [Java 7](http://www.oracle.com/technetwork/java/javase/downloads/jdk7-downloads-1880260.html)
 - [javacv 0.11](https://github.com/bytedeco/javacv)

## Introduction
easyPR是[liuruoze/EasyPR](https://github.com/liuruoze/EasyPR) 的Java版本。
easyPR是一个中文的开源车牌识别系统，其目标是成为一个简单、高效、准确的车牌识别引擎。

假设我们有如下的原始图片，需要识别出中间的车牌字符与颜色：

![EasyPR 原始图片](http://www.wailian.work/images/2018/05/04/plate_recognize.jpg)

经过easyPR的第一步处理车牌检测（PlateDetect）以后，我们获得了原始图片中仅包含车牌的图块：

![EasyPR 车牌](http://www.wailian.work/images/2018/05/04/chars_segment.jpg)

接着，我们对图块进行OCR过程，在easyPR中，叫做字符识别（CharsRecognize）。我们得到了一个包含车牌颜色与字符的字符串：

“蓝牌：苏EUK722”

## How to play
- `EasyPrTest`

## Tips
- Commits on Jun 2, 2015
- Added Globals for Windows

## Links
- [EasyPR-Java](https://github.com/fan-wenjie/EasyPR-Java)