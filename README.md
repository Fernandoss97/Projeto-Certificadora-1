# Solve Physics

## Equipe

A equipe é composta pelos seguintes integrantes:

- Daniel Seidenthal
- Fernando Santos Nascimento
- Igor Gustavo Mainardes
- Jhonatan Bruno Viza Atahuichy
- Vinicius Cerrone

## Descrição do projeto

O projeto consiste em um sistema desktop onde o usuário pode resolver problemas de Física do ensino médio.
Os problemas são divididos em 3 níveis de dificuldade (nível 1, nível 2 e nível 3) com um sistema de pontuação baseado nesses níveis:

```
- Para cada acerto de problemas do nível 1 o usuário recebe 100 pontos;
- Para cada acerto de problemas do nível 2 o usuário recebe 250 pontos;
- Para cada acerto de problemas do nível 3 o usuário recebe 500 pontos;

Caso o usuário escolha um problema que já foi resolvido:

-  A cada nova tentativa a pontuação do problema é dividida por 2.

```

O sistema também implementa algumas regras que devem ser seguidas pelo usuário:

```
- O usuário pode resolver um problema com nível de dificuldade 2 após resolver pelo menos um problema do nível 1;

- O usuário pode resolver um problema do nível 3 após resolver pelo menos um problema do nível 2;

- Após resolver um problema 3, o usuário poderá resolver quaisquer problemas;
```

## Repositório do projeto

O repositório do projeto pode ser acessado **[através desse site](https://github.com/Fernandoss97/Projeto-Certificadora-1.git "através desse site")**

## Instalação e execução

Este é um projeto Java desenvolvido no ambiente NetBeans.
Antes de prosseguir, certifique-se de que você tenha instalado as seguintes ferramentas em seu sistema:

```
 - Java Development Kit (JDK)
 - NetBeans IDE
 - PostgreSQL
```

Siga as etapas abaixo para instalar e executar o projeto:

1. Faça o download do repositório do projeto

2. Configure o banco de dados:

```
- Instale o PostgreSQL em sua máquina

- Crie um banco de dados vazio no PostgreSQL que será usado pelo projeto.

- Procure por um script de configuração de banco de dados no diretório do projeto.

- Para que o PostgreSQL tenha acesso aos arquivoso de imagem, pode ser preciso mover a pasta `Imagens` para o diretório onde foi instalado o PostgreSQL, geralmente no caminho: `C:\Program Files\PostgreSQL\16\data`. Se esse for o seu caso, mova a pasta referida para esse caminho, se não, identifique o diretório de instalação e ralize o processo de mover a pasta Imagens.

- No script, edite o caminho das imagens, colocando o caminho correto para cada uma das imagens do diretório `Imagens`.

- Execute esse script no PostgreSQL

- Certifique-se de que o servidor local sql esteja funcionando corretamente

```

3. Abra o NetBeans IDE.

4. Vá em "Arquivo" > "Abrir Projeto" e navegue até a pasta onde você baixou o repositório. Selecione o arquivo de projeto (normalmente com extensão .nbproject) e clique em "Abrir Projeto".

5. Procure pelo arquivo `ModuloConexao` e edite as credenciais de acesso ao servidor local sql (url, usuário e senha).

Após realizar as configurações você pode executar o projeto no NetBeans.

Siga as instruções específicas do projeto para interagir com o sistema ou teste suas funcionalidades.
