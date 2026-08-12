# 🔧 Vazão Industrial — IndustrialFlowCalculator

Aplicativo Android nativo para cálculos de vazão industrial, desenvolvido em **Kotlin + Jetpack Compose (Material 3)**. 100% offline, ideal para uso em campo por engenheiros e técnicos de instrumentação.

## 📱 Funcionalidades

O app oferece 6 calculadoras distintas:

| # | Cálculo | Fórmula |
|---|---------|---------|
| 1 | **Vazão Volumétrica** | Q = A × v |
| 2 | **Tubulação Circular** | A = π(D/2)², Q = A × v |
| 3 | **Vazão Mássica** | ṁ = ρ × Q |
| 4 | **Conversão de Unidades** | m³/h ↔ L/min ↔ L/s ↔ GPM |
| 5 | **Velocidade do Fluido** | v = Q / A |
| 6 | **Número de Reynolds** | Re = (ρ × v × D) / μ |

### Detalhes dos cálculos

1. **Vazão Volumétrica**: Informe a área da seção transversal (m²) e a velocidade do fluido (m/s). Resultado em m³/s, m³/h, L/min e L/s.

2. **Tubulação Circular**: Informe o diâmetro interno do tubo (mm) e a velocidade (m/s). O app calcula automaticamente a área e a vazão em múltiplas unidades.

3. **Vazão Mássica**: Informe a densidade do fluido (kg/m³) e a vazão volumétrica. Selecione a unidade de entrada. Resultado em kg/s, kg/min, kg/h e t/h.

4. **Conversão de Unidades**: Informe um valor e selecione a unidade de origem. O app converte para todas as demais unidades simultaneamente.

5. **Velocidade do Fluido**: Informe a vazão volumétrica e o diâmetro do tubo. Resultado em m/s e km/h.

6. **Número de Reynolds**: Informe densidade, velocidade, diâmetro e viscosidade dinâmica. O app calcula Re e classifica o regime de escoamento (Laminar < 2000 | Transição 2000–4000 | Turbulento > 4000).

## 🛠️ Tecnologias

- **Linguagem**: Kotlin
- **UI**: Jetpack Compose + Material 3
- **Arquitetura**: MVVM (ViewModel + StateFlow)
- **minSdk**: 24 (Android 7.0+)
- **targetSdk**: 34
- **Modo**: 100% offline

## ⬇️ Como baixar o APK

O APK debug é gerado automaticamente pelo GitHub Actions a cada push na branch `main`.

### Passo a passo:

1. Acesse a aba **Actions** neste repositório
2. Clique no **run mais recente** (verde ✅)
3. Role a página até a seção **Artifacts**
4. Clique em **IndustrialFlowCalculator-APK** para baixar
5. Extraia o arquivo `.zip` — o APK estará dentro
6. Instale no Android (pode ser necessário habilitar "Fontes desconhecidas")

## 🏗️ Build local

### Pré-requisitos
- JDK 17
- Android SDK (API 34)

### Comandos
```bash
git clone <url-do-repositório>
cd IndustrialFlowCalculator
chmod +x ./gradlew
./gradlew assembleDebug
```

O APK será gerado em: `app/build/outputs/apk/debug/app-debug.apk`

## 📋 Estrutura do projeto

```
IndustrialFlowCalculator/
├── app/src/main/java/com/example/industrialflowcalculator/
│   ├── MainActivity.kt
│   ├── ui/
│   │   ├── screens/          # 7 telas (Home + 6 cálculos)
│   │   ├── theme/            # Tema Material 3
│   │   └── components/       # Componentes reutilizáveis
│   └── viewmodel/            # 6 ViewModels
└── .github/workflows/
    └── android-apk.yml       # CI/CD para geração do APK
```

## 📄 Licença

Este projeto é de uso livre para fins educacionais e industriais.
