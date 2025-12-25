# Backend Challenge - Validador de Senha

## 📅 Cronograma de Desenvolvimento

### Dia 22/12 - Planejamento e Arquitetura
- [x] Definição de prazos e entregas por dia
- [x] Definição da arquitetura (Clean Architecture)
- [x] Documentar motivo da escolha da arquitetura
- [x] Criação das pastas do projeto
- [x] Enviar link do repositório para Tainara Ramim

### Dia 23/12 - Desenvolvimento Core
- [X] Criação do endpoint REST
- [X] Implementação das regras de negócio
- [X] Criação do Use Case
- [X] Documentar decisões técnicas tomadas
- [X] Implementar controller advice

### Dia 24/12 - Revisão Arquitetural
- [X] Verificar: Abstração, acoplamento, extensibilidade e coesão
- [X] Validar princípios SOLID
- [X] Refatorações necessárias

### Dia 25/12 - Testes
- [X] Criação de testes unitários
- [X] Criação de testes de integração
- [X] Configuração do JaCoCo para cobertura

### Dia 26/12 - Aspectos Não-Funcionais
- [ ] Avaliar necessidade de cache e documentar
- [ ] Implementar aspectos de segurança
- [ ] Documentar decisões de segurança

### Dia 27/12 - Revisão
- [ ] Validar isolamento do domínio
- [ ] Documentar como executar o código
- [ ] Disponibilizar exemplos de cURL
- [ ] Checklist final do projeto

### Dia 28/12 - Finalização
- [ ] Criação de log simples ou estruturados, a depender da prazo
- [ ] Se possível, criar swagger
- [ ] Avisar a Tainara Ramim que o projeto foi finalizado
- [ ] Propor adiantamento da apresentação, se a agenda permitir

## ❓ Motivos
<details>
  <summary>Por que Clean Architecture?</summary>

1. **Proporcionalidade**: Como o projeto tem somente uma função e não tem integração (filas, APIs, etc), a Clean Arch proporciona separação suficiente, suporta eventuais crescimento do projeto e o projeto não fica com um overengineering
2. **Testabilidade**: Facilita testes unitários e de integração em cada camada
3. **Extensibilidade**: Fácil adição de novas regras de validação
4. **Flexibildade**: Separa bem regras de negócio de detalhes de implementação
</details>

<details>
  <summary>Por que POST e não GET?</summary>

1. `GET`: é para recuperação de dados, senha ficaria exposta na url, logs e historico do navegador
2. `POST`: é para processar/validar dados; mais seguro, permitindo o envio num JWT assinado; Facilita eventuais extensões;
</details>

<details>
  <summary>Por que Chain of responsibility?</summary>

- Chain of responsibility é um Design Pattern popular e conhecido por gerenciar chamadas em cadeia, ainda que as business rule
não sejam chamadas em cadeia - com uma rule chamando outra - é possível se inspirar no patterns para adaptá-lo à nossa realidade.

</details>

<details>
  <summary>Por que usar gradle?</summary>

- Mais performático, pois usa paralelismo e cache
- Comumente usado com o Kotlin
- Suporte nativo ao Kotlin

</details>

### Estrutura do Projeto

```
src/
├── main/java/com/itau/challenge/
│   ├── domain/
│   │   ├── entities/
│   │   ├── valueobjects/
│   │   └── services/
│   ├── application/
│   │   ├── usecases/
│   │   ├── interfaces/
│   │   └── dtos/
│   ├── infrastructure/
│   │   └── web/
│   └── PasswordValidatorApplication.java
└── test/
```

### Camadas e Responsabilidades

- **Domain**: Regras de negócio puras, entities e value objects
- **Application**: Use cases e interfaces (ports)
- **Infrastructure**: Controllers, configurações e adapters

---
Check List/Débitos (apagar)
- [ ] Revisar no README a parte de `Estrutura do Projeto`
- [X] Parametrizar a quantidade minima de maiusculo, minusculo, char especial e digito, assim fica dinamico.
- [X] Ajusta exception retornada por notNull notBlank
- [X] Explicar o uso do gradle
---
*Projeto em desenvolvimento - Atualizações diárias conforme cronograma*