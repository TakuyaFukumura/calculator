# Calculator Web Application

A web-based calculator application built with Spring Boot that provides basic arithmetic operations through a clean and intuitive user interface.

![Calculator Interface](https://github.com/user-attachments/assets/6807c904-8ae4-4042-b1ec-45b1e54f7b2b)

## 概要 (Overview)

このプロジェクトは、
Spring Bootフレームワークを使用して構築されたWebベースの電卓アプリケーションです。
基本的な四則演算（加算、減算、乗算、除算）をサポートし、
精密な計算のためにBigDecimalを使用しています。

This project is a web-based calculator application built using the Spring Boot framework.
It supports basic arithmetic operations (addition, subtraction, multiplication, division) and uses BigDecimal for precise calculations.

## 特徴 (Features)

- ✅ 基本的な四則演算 (Basic arithmetic operations)
  - 加算 (Addition: ＋)
  - 減算 (Subtraction: -)
  - 乗算 (Multiplication: ×)
  - 除算 (Division: ÷)
- ✅ 小数点計算のサポート (Decimal number support)
- ✅ エラーハンドリング (Error handling)
  - ゼロ除算エラー (Division by zero)
  - オーバーフロー検出 (Overflow detection)
- ✅ セッションベースの計算履歴 (Session-based calculation history)
- ✅ 全削除 (All Clear: AC) と 1文字削除 (Clear: C) 機能
- ✅ レスポンシブなWebインターフェース (Responsive web interface)
- ✅ 高精度計算 (High precision calculation using BigDecimal)

## 技術スタック (Technology Stack)

- **Backend**: Spring Boot 3.3.3
- **Template Engine**: Thymeleaf
- **Language**: Java 17
- **Build Tool**: Maven
- **Testing**: JUnit 5
- **Styling**: CSS
- **Development Tools**: Lombok

## 前提条件 (Prerequisites)

- Java 17 or higher
- Maven 3.6+ (Maven Wrapperが含まれているため不要 / Not required as Maven Wrapper is included)

## インストールと実行 (Installation and Running)

### 1. リポジトリのクローン (Clone the repository)

```bash
git clone https://github.com/TakuyaFukumura/calculator.git
cd calculator
```

### 2. アプリケーションのビルドと実行 (Build and run the application)

```bash
# Mavenを使用してビルド (Build using Maven)
./mvnw clean compile

# アプリケーションの実行 (Run the application)
./mvnw spring-boot:run
```

### 3. ブラウザでアクセス (Access in browser)

アプリケーションが起動したら、以下のURLにアクセスしてください:
Once the application starts, access the following URL:

```
http://localhost:8080
```

## 使用方法 (Usage)

1. **数字入力**: 0-9の数字ボタンをクリックして数値を入力
2. **演算子**: ＋、-、×、÷ ボタンで演算子を選択
3. **小数点**: . ボタンで小数点を入力
4. **計算実行**: ＝ ボタンで計算を実行
5. **クリア**: 
   - C ボタン: 最後の1文字を削除
   - AC ボタン: 全ての入力をクリア

### 計算例 (Calculation Examples)

```
12 + 34 = 46
100 - 25 = 75
7 × 8 = 56
15 ÷ 3 = 5
3.14 + 2.86 = 6
```

## テストの実行 (Running Tests)

```bash
# 全てのテストを実行 (Run all tests)
./mvnw test

# 特定のテストクラスを実行 (Run specific test class)
./mvnw test -Dtest=CalculationServiceTest
```

現在35個以上のテストケースがあり、計算ロジックの正確性を保証しています。
Currently, there are 35+ test cases ensuring the accuracy of calculation logic.

## プロジェクト構造 (Project Structure)

```
src/
├── main/
│   ├── java/com/example/calculator/
│   │   ├── Calculator.java                 # メインアプリケーションクラス
│   │   ├── controller/
│   │   │   └── IndexController.java        # Webコントローラー
│   │   ├── service/
│   │   │   ├── CalculationService.java     # 計算ロジック
│   │   │   ├── InputService.java           # 入力処理
│   │   │   └── DeleteService.java          # 削除処理
│   │   ├── dto/
│   │   │   └── CalculationHistory.java     # 計算履歴DTO
│   │   ├── enums/
│   │   │   └── Operator.java               # 演算子列挙型
│   │   ├── constants/
│   │   │   └── Constants.java              # 定数クラス
│   │   └── util/
│   │       └── CommonUtil.java             # ユーティリティ
│   └── resources/
│       ├── templates/
│       │   └── index.html                  # メインページテンプレート
│       └── static/css/
│           └── design.css                  # スタイルシート
└── test/                                   # テストクラス群
```

## 開発 (Development)

### ビルド (Build)
```bash
./mvnw clean compile
```

### パッケージング (Packaging)
```bash
./mvnw clean package
```

### 実行可能JARの作成 (Create executable JAR)
```bash
./mvnw clean package
java -jar target/calculator.jar
```

## CI/CD

このプロジェクトはGitHub Actionsを使用した継続的インテグレーションが設定されています:
This project is configured with continuous integration using GitHub Actions:

- **自動ビルド**: プッシュとプルリクエスト時にMavenビルドを実行
- **自動テスト**: 全てのテストケースを自動実行
