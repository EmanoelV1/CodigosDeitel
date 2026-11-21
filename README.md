# Produtor/Consumidor e Mailbox em Java - Sistemas Operacionais

Atividade prática sobre Threads, Concorrência e Comunicação entre Processos (IPC) baseada nos estudos de caso do livro *Sistemas Operacionais* (Deitel) e conceitos de troca de mensagens (Maziero).

## Sobre o Projeto

O repositório compara dois cenários de comunicação entre as threads `Producer` (que produz valores de 1 a 4) e `Consumer` (que lê e soma esses valores):

1. **Sem Sincronização (`SharedBufferTest`):** Usa uma variável inteira simples (`UnsynchronizedBuffer`) sem controle de concorrência. Causa condição de corrida (*race condition*), perda de valores por sobrescrita e leituras duplicadas, alterando a soma final a cada execução.
2. **Buffer Seguro com Mailbox (`MailboxBufferTest`):** Introduz uma caixa de correio / fila de mensagens (`MessageQueue`) sincronizada com Monitor Java (`synchronized`, `wait()` e `notifyAll()`). Garante que nenhuma mensagem seja perdida ou lida duas vezes, resultando sempre na soma correta (`10`).

## Estrutura dos Arquivos

* `Buffer.java`: Interface com os métodos `set` e `get`.
* `Producer.java`: Thread produtora.
* `Consumer.java`: Thread consumidora.
* `UnsynchronizedBuffer.java`: Buffer simples sem sincronização.
* `SharedBufferTest.java`: Executa o teste sem sincronização.
* `MessageQueue.java`: Estrutura de fila (FIFO) usada como Mailbox (`send` e `receive`).
* `MailboxBufferTest.java`: Implementa o `SafeMailboxBuffer` sincronizado e executa o teste seguro.

## Como Executar

Compile os arquivos na pasta `src`:

```bash
javac *.java
```

Para rodar o teste **sem sincronização**:

```bash
java SharedBufferTest
```

Para rodar o teste **com Mailbox seguro**:

```bash
java MailboxBufferTest
```

## Autores

* **André Barbante**
* **Emanoel Vieira**
* **Phoebe Regina**
