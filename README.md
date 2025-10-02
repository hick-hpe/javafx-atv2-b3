# Java FX no Visual Studio Code

JavaFX é uma plataforma de aplicativos cliente de última geração, de código aberto, para desktops, dispositivos móveis e sistemas embarcados, desenvolvida em Java. É um esforço colaborativo de diversas pessoas e empresas com o objetivo de produzir um kit de ferramentas moderno, eficiente e completo para o desenvolvimento de aplicativos cliente avançados.

## Instalação
- Acessar o site do Java FX:
    - [https://openjfx.io/](https://openjfx.io/)
- Ir para Downloads
    - [https://gluonhq.com/products/javafx/](https://gluonhq.com/products/javafx/)
- Marcar o checkbox `Include archived versions`
- Baixar o SDK da versão do Java instalado na máquina:
    - Para ver a versão: `java --version`
- Baixar o SDK

## Configuração no VS Code
- Criar a seguinte estrutura:

        projeto/
        ├── lib/
        ├── src/
        │   └── Main.java
        └── out/

- No diretório no qual foi baixado o SDK:
    - Extraia o arquivo
    - Acesse o diretório até a pasta `lib/`
    - Copie todos os arquivos para o diretório `lib/` do seu projeto
- Abra o terminal neste projeto e execute:
    - Compilação
        ```java
        javac --module-path lib --add-modules javafx.controls -d out src/Main.java
        ```
    - Execução
        ```java
        java --module-path lib --add-modules javafx.controls -cp out Main
        ```
- Para executar os dois comandos anteriores automaticamente:
    - Crie um arquivo `run.sh`
        ```bash
        #!/bin/bash

        # compilar    
        javac --module-path lib --add-modules javafx.controls -d out src/Main.java

        # executar
        java --module-path lib --add-modules javafx.controls -cp out Main
        ```
    - Coloque permissões para executar:
        ```bash
        chmod +x run.sh
        ```
    - Execute o script:
        ```bash
        source run.sh
        ```
