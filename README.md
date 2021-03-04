# SRTF
Shortest Remaining Time Firstのターンアラウンドタイム及び平均ターンアラウンドタイムの計算

# Features

ジョブの処理アルゴリズムの一つであるSRTFにおいてのターンアラウンドタイム及び平均ターンアラウンドタイムを求めることができる。

# Requirement

* openjdk 14.0.2

# Usage

* コンパイルコマンド
```bash
javac -encoding UTF-8 SRTF.java
```

* 実行コマンド
```bash
java SRTF
```

* 入力データ例
```bash
4
0 7
2 6
5 1
7 2
```

# Note

* 入力形式
入力するデータは、1行目にはデータの個数"n"、2行目以降にはジョブの"到着時刻 処理時間"の順
```bash
n
Job[0]_ARV Job[0]_TIME
Job[1]_ARV Job[1]_TIME
...
```

# Author

* https://github.com/kokugosogo
* kokugosogo.11@gmail.com
