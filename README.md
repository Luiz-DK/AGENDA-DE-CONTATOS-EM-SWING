# Agenda de Contatos — Aplicação Desktop em Java com Swing (Projeto Acadêmico)

[![UniRV](https://img.shields.io/badge/UniRV-Universidade%20de%20Rio%20Verde-red?style=for-the-badge)](#)
[![Disciplina](https://img.shields.io/badge/Disciplina-Programação%20Orientada%20a%20Objetos-blue?style=for-the-badge)](#)
[![Curso](https://img.shields.io/badge/Curso-Engenharia%20de%20Software-orange?style=for-the-badge)](#)
[![Atividade](https://img.shields.io/badge/Atividade-Roteiro%20Guiado-brightgreen?style=for-the-badge)](#)
[![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](#)
[![Swing](https://img.shields.io/badge/GUI-Swing-8B5CF6?style=for-the-badge)](#)
[![Persistência](https://img.shields.io/badge/Persistência-Arquivo%20TXT-lightgrey?style=for-the-badge)](#)
[![Tema](https://img.shields.io/badge/Tema-Dark%20Purple-black?style=for-the-badge&labelColor=8B5CF6)](#)
[![Status](https://img.shields.io/badge/Status-Concluído-brightgreen?style=for-the-badge)](#)

Este repositório contém o código da **Agenda de Contatos**, uma aplicação desktop desenvolvida em **Java com Swing** como atividade prática da disciplina de **Programação Orientada a Objetos** da **Universidade de Rio Verde (UniRV)**. O programa cadastra contatos (nome, telefone e cidade), grava tudo em um arquivo de texto e exibe a lista salva na própria janela.

---

## 📌 Contexto Acadêmico

- **Instituição:** Universidade de Rio Verde (UniRV) — Campus Rio Verde
- **Curso:** Engenharia de Software
- **Disciplina:** Programação Orientada a Objetos (ESW448)
- **Conteúdo:** Interface Gráfica com Swing, Eventos e Tratamento de Exceções
- **Atividade:** Atividade Prática — Agenda de Contatos em Swing (Roteiro Guiado), trabalho individual
- **Objetivo da Atividade:** Construir uma tela com `JFrame` e `ActionListener`, separar a interface da lógica de arquivo, validar a entrada do usuário e tratar exceções de entrada e saída sem travar o programa.

---

## 🎯 Solução Proposta: Agenda de Contatos

A aplicação oferece um formulário simples para cadastrar contatos e um botão para consultar a agenda, sem abrir uma segunda janela. Os dados ficam salvos em `contatos.txt`, então permanecem entre uma execução e outra.

### 💡 Problemas que o programa resolve:
- **Perda de dados ao fechar o programa:** Cada contato é gravado em arquivo de texto e lido de volta quando a lista é aberta.
- **Cadastros incompletos:** Campos vazios (ou só com espaços) são barrados com um aviso que diz **o quê** está errado, **onde** (o foco volta ao campo) e **como** corrigir.
- **Erros de gravação que passam despercebidos:** Se o arquivo não puder ser salvo, a tela mostra uma mensagem de erro e **não limpa os campos**, para o usuário não perder o que digitou.
- **Interface misturada com acesso a arquivo:** A tela não abre arquivo nenhum; ela só chama a classe `AgendaArquivo`, que cuida de toda a leitura e gravação.

---

## ✨ Critérios da Atividade Atendidos

| Critério | Implementação na Agenda de Contatos | Status |
| :--- | :--- | :---: |
| **Separação de responsabilidades** | A `TelaContato` só chama `AgendaArquivo`; nenhuma classe de arquivo (`File`, `Reader`, `Writer`) aparece na tela. | ✅ |
| **Herança e interface** | `TelaContato extends JFrame implements ActionListener`. | ✅ |
| **Componentes como atributos** | Campos, botões e tabela são atributos `private` da tela. | ✅ |
| **Layout** | `GridLayout(4, 2, 8, 8)` dentro de `BorderLayout`, borda de 24 e nenhum `setBounds`. | ✅ |
| **Eventos** | Os dois botões usam `addActionListener(this)` e `e.getSource()` separa os cliques. | ✅ |
| **Validação** | `trim()` + `isEmpty()` geram aviso com o quê, onde e como, e nada é gravado. | ✅ |
| **Tratamento de exceção** | `catch (IOException ex)` na tela, com `JOptionPane` de erro; os campos não são limpos. | ✅ |
| **Onepage** | O botão "Ver contatos" mostra ou esconde a lista na mesma janela. | ✅ |
| **Execução** | O programa compila, abre a janela, salva e lista os contatos. | ✅ |
| **Teste de compreensão** | As quatro respostas comentadas no final da `TelaContato`. | ✅ |

---

## 🛠️ Tecnologias Utilizadas

- **Java:** Programação orientada a objetos com encapsulamento, herança e implementação de interface.
- **Swing / AWT:** `JFrame`, `JPanel`, `JTextField`, `JButton`, `JTable` e `JOptionPane`, com layouts `BorderLayout` e `GridLayout`.
- **Java I/O:** `FileWriter` em modo *append*, `PrintWriter`, `BufferedReader` e `FileReader`, com `try-with-resources`.
- **Formato de dados:** Uma linha por contato, no padrão `nome;telefone;cidade`.
- **Tema visual:** Modo escuro com fundo preto e detalhes em roxo, aplicado por código (cores, bordas, renderizadores da tabela e `UIManager` para os diálogos).

---

## 🧩 Como o código está organizado

| Classe | Responsabilidade |
| :--- | :--- |
| `Contato` | Guarda nome, telefone e cidade; converte de e para linha de texto (`toLinha` e `fromLinha`). |
| `AgendaArquivo` | Adiciona contatos ao arquivo, lista todos e busca por cidade. |
| `TelaContato` | Monta a janela, valida os campos, trata eventos e exceções e atualiza a tabela. |

---

## ▶️ Como Executar

Com o JDK instalado, dentro da pasta do projeto:

```bash
javac *.java
java TelaContato
```

Mantenha o `contatos.txt` na pasta de onde o programa é executado. Se o arquivo não existir, ele é criado ao salvar o primeiro contato.

---

## ⚠️ Limitações Conhecidas

- **Ponto e vírgula no texto:** Como `;` separa os campos no arquivo, um nome como `Ana; Maria` gera 4 partes na linha e desloca os dados na leitura. A análise completa está no teste de compreensão, no final da `TelaContato`.
- **Linhas em branco no arquivo:** Uma linha vazia em `contatos.txt` não pode ser convertida em contato.
- **Busca por cidade:** O método `buscarPorCidade` existe na `AgendaArquivo`, mas a tela ainda não oferece esse filtro.

---

## 📁 Estrutura de Arquivos

```text
agenda-contatos/
├── TelaContato.java        # Interface gráfica (JFrame + ActionListener)
├── AgendaArquivo.java      # Leitura e gravação no arquivo de texto
├── Contato.java            # Modelo de dados do contato
├── contatos.txt            # Arquivo de dados (um contato por linha)
└── README.md               # Documentação técnica e acadêmica do projeto
```
