# Stonks

## Introdução

Stonks é um projeto mobile desenvolvido para a plataforma Android, utilizado para aplicar meus conhecimentos mais recentes sobre a plataforma, abordando estratégias de arquitetura, componentes e ferramentas mais atuais como Jetpack Compose.

## TLDR;

Para o processo de instalação e execução do projeto, instale a versão mais recente do [Android Studio](https://developer.android.com/studio) e ao carregar o projeto, siga os passos abaixo:

- Certifique-se que o arquivo gerando na pasta `.gradle/config.properties` contém o mesmo caminho presente na variável de ambiente `JAVA_HOME` configurado em seu usuário;
- Execute o script de setup do projeto para instalação do hook de *pre-commit* responsável pela execução do detekt:
- Acesse o arquivo `build.gradle` do módulo network e informe a chave de autorização do GitHub no [AUTHORIZATION_KEY](https://github.com/jonathanarodr/stonks/blob/main/infrastructure/network/build.gradle.kts#L15) ([saiba mais](https://docs.github.com/en/rest/authentication/authenticating-to-the-rest-api?apiVersion=2022-11-28#basic-authentication))

```
sh ./tools/setup.sh
```

> [!CAUTION]
> Caso esteja executando o projeto em um ambiente com restrições de rede com proxy, certifique-se que as dependências utilizadas no gerenciador de dependências possui as versões utilizadas por este projeto ([saiba mais](https://github.com/jonathanarodr/stonks/blob/main/gradle/libs.versions.toml)).

## 🚩 Features

- [x] Módularização: distribuição funcional entre os módulos ([leia mais](#modularização));
- [x] Home: feature responsável pela exibição da carteira de ativos e histórico transacional;
- [x] Stock alert: feature responsável pela gestão de alerta de ativos;
- [x] Comparador de preços: feature responsável pela comparação de preços do ativo x alerta;
- [ ] Push: motor responsável pelo disparo de push's para o cliente;

## 📚 Tech Stack

- Aplicação desenvolvida em [Kotlin](https://kotlinlang.org/)
- Build system utilizando Gradle com [Conventional Plugins](https://docs.gradle.org/current/userguide/composite_builds.html) e [Version Catalog](https://docs.gradle.org/current/userguide/platforms.html)
- Componentes visuais inteiramente desenvolvidos com [Jetpack Compose](https://developer.android.com/develop/ui/compose)
- Requisições assíncronas desenvolvidas com [Coroutines](https://developer.android.com/kotlin/coroutines)
- Injeção de dependência com [Koin](https://insert-koin.io/)
- Armazenamento interno com [Room](https://developer.android.com/training/data-storage/room)

## 🧪 Quality

- [Detekt](https://detekt.dev/)
- [Detekt Formatting](https://detekt.dev/docs/rules/formatting/)
- [Compose Rules](https://github.com/mrmans0n/compose-rules)

> [!TIP]
> Para executar o analisador de cófigo estático, na raiz do projeto execute o seguinte comando:

```
./gradlew detekt
```

## 📈 Coverage

Para geração do relatório de cobertura de testes, foi aplicado o uso da biblioteca [Kover](https://github.com/Kotlin/kotlinx-kover).

> [!TIP]
> Para executar o relatório de cobertura de todos os módulos, na raiz do projeto execute o seguinte comando:

```
./gradlew koverHtmlReportDebug
```

# 🏗️ Arquitetura

A arquitetura do projeto foi baseada nas práticas recomendadas pelo [Android Guide Arch](https://developer.android.com/topic/architecture/recommendations) abordando práticas como SOLID, MVVM, Single source of truth (SSOT), Unidirectional Data Flow (UDF) e práticas de modularização funcional.

## ♻️ Modularização

- `:app`: módulo principal do aplicativo responsável por conter toda lógica de inicialização do app;
- `gradle-build`: módulo responsável pela configuração de build system do projeto provendo plugins reutilizáveis em qualquer novo módulo `kotlin-library` ou `android-library` eliminando duplicações de scripts de build;
- `:testing` : módulo responsável pelas lógicas comuns para desenvolvimento de testes, deve ser utilizado somente com `testImplementation` ou `androidTestImplementation`;
- `:common` : módulo responsável pelas lógicas comuns entre os módulos, como abstrações genéricas, formatadores, utilitários, etc;
- `:design-system` : módulo responsável por contér design tokens e componentes reutilizáveis entre as features;
- `:infrastructure:network` : módulo responsável por conter lógicas de estruturais do projeto, atualmente contendo apenas lógicas relacionadas a camada de serviço;
- `:feature:home` : módulo responsável por contér lógicas da feature home;
- `:feature:stocks` : módulo responsável por contér lógicas da feature de alerta de ativos/produtos;

A modularização funcional dos módulos no Gradle é essencial para a organização, reutilização e manutenção eficiente do código. Por este motivo este projeto utiliza a biblioteca [Module Graph Assert](https://github.com/jraska/modules-graph-assert) que auxilia neste processo.

> [!TIP]
> Para executar o a análise do grafo ou relatório de distribuição dos módulos, na raiz do projeto execute os seguintes comandos:

```
# analyzing dependency graph
./gradlew assertModuleGraph

# generating dependency graph
./gradlew generateModulesGraphvizText
```

## 📦 Automação

Com o objetivo de automatizar o processo de contribuição neste projeto, foi incluído um [workflow](https://github.com/jonathanarodr/stonks/actions) via [GitHub Actions](https://docs.github.com/en/actions) responsável por executar todos os gates de qualidade e build dos artefatos.
