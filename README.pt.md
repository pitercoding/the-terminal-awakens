<h1 align="center">
  👁️🖥️ The Terminal Awakens 🖥️👁️
</h1>

<p align="center">
  🌎 <strong>Languages:</strong><br>
  <a href="README.pt.md">🇧🇷 Portuguese</a> |
  <a href="README.md">🇺🇸 English</a>
</p>

**The Terminal Awakens** é um **jogo de RPG baseado em terminal desenvolvido em Java**, onde o jogador explora um mundo estilo dungeon, enfrenta monstros e evolui seu personagem através de XP, níveis e equipamentos.

O projeto possui:

- Múltiplas **vocações/classes**: Knight, Paladin, Druid, Sorcerer  
- **Combate em turnos** com ataques especiais e gerenciamento de mana  
- **Sistema de equipamentos**: Armas e armaduras com bônus  
- **Inventário e itens**: Poções de Vida e Mana  
- **Exploração de mapa**: Encontros aleatórios, lojas, NPCs e boss final  
- **Retratos em ASCII** e interface textual imersiva  

---

## 🏆 Motivação

Este projeto foi criado como um **projeto pessoal de aprendizado em Java**, com foco em:

- Praticar **POO, herança e polimorfismo**  
- Desenvolver **jogos em terminal** com estrutura de código limpa  
- Implementar **mecânicas de combate, inventário e progressão de nível**  
- Explorar **balanceamento de jogo, UX e gráficos em terminal**  

Também serviu para reforçar conceitos como:

- Design de **hierarquias de classes e classes abstratas** para reutilização  
- Gerenciamento de **loops de jogo, eventos e estados do jogador**  
- Implementação de **sistema de equipamentos, itens e atributos** em um contexto de RPG  

---

## 📸 Screenshots

Abaixo estão algumas capturas de tela mostrando momentos importantes da jogabilidade de **The Terminal Awakens**.

### 1. 🖥️ Início do Jogo — Tela ANSI
Arte em ASCII exibida ao iniciar o jogo, apresentando o mundo de **The Terminal Awakens**.

![Game Start](screenshots/game-start.png)

---

### 2. 🧙 Criação de Personagem & Kit Inicial
O início da jornada, onde o jogador escolhe sua vocação e recebe os equipamentos iniciais.

![Character Creation](screenshots/character-creation.png)

---

### 3. ⚔️ Exemplo de Combate
Um cenário de combate em turnos contra inimigos comuns.

![Combat](screenshots/combat.png)

![Combat 2](screenshots/combat2.png)

---

### 4. 🎒 Visualização do Inventário
Visualização e uso de itens como Poções de Vida e Mana durante o jogo.

![Inventory View](screenshots/inventory-view.png)

---

### 5. 🗺️ Exploração do Mapa
Exploração do mapa, descobrindo caminhos, encontros e eventos ocultos.

![Map Exploration](screenshots/map-exploration.png)

---

### 6. 🐉 Encontro com o Boss
Uma batalha perigosa contra o boss final, testando estratégia, gerenciamento de recursos e sobrevivência.

![Boss Encounter](screenshots/boss-terminal-of-vortex.png)

---

## 📚 Pontos de Aprendizado

Durante o desenvolvimento, os seguintes conceitos foram reforçados:

- **Backend / Lógica de Jogo (Java)**  
  - Classes abstratas e polimorfismo para personagens  
  - Mecânicas de combate e sistema em turnos  
  - Sistema de equipamentos e inventário  
  - XP, progressão de nível e crescimento de atributos  
  - Mapa, tiles e gerenciamento de eventos  

- **UX em Terminal & Arte ASCII**  
  - Exibição dinâmica de status e combate  
  - Inventário agrupado e mensagens narrativas  
  - Retratos de monstros e bosses  
  - Impressão lenta no console para maior imersão  

---

## 🚀 Como Executar Localmente

### 📦 Pré-requisitos

Certifique-se de ter:

- **Java 21+** instalado  
- **Terminal / Prompt de Comando**  

### 🖥️ Executar o Jogo

1. Compile o projeto:

```bash
javac -d out/production/the-terminal-awakens src/main/java/com/terminalawakens/**/*.java
```

2. Execute o jogo:
```bash
java -cp out/production/the-terminal-awakens com.terminalawakens.Main
```
3. Siga as instruções no terminal para:
   - Criar seu personagem
   - Explorar o mapa usando as teclas AWSD
   - Lutar contra monstros
   - Visitar lojas e coletar loot
   - Enfrentar o boss final **Terminal of Vortex**

## 🧱 Estrutura do Projeto
```bash
the-terminal-awakens/
├─ src/main/java/com/terminalawakens/
│  ├─ character/          # Classes de personagens, kit inicial, atributos, equipamentos
│  ├─ creatures/          # Monstros, bosses, MonsterFactory
│  ├─ engine/             # GameEngine & CombatEngine
│  ├─ equipment/          # Armas, armaduras e lógica de equipamentos
│  ├─ items/              # Poções e ItemFactory
│  ├─ shop/               # Loja de itens e equipamentos
│  ├─ util/messages/      # Retratos ASCII, SlowConsole, GameStart/GameEnd
│  └─ world/              # Mapa do jogo, tipos de tiles e controle de posição
├─ out/                   # Arquivos .class compilados
├─ screenshots/           # Pasta de screenshots
├─ .gitignore
├─ LICENSE
├─ README.md
└─ README.pt.md
```

## 🎮 Fluxo de Gameplay
```text
Criação de Personagem
 ↓
Exploração do Mapa (AWSD)
 ↓
Eventos Aleatórios:
   - Encontro com Monstro → CombatEngine
   - Tile de Loot → Coleta de itens
   - Tile de Loja → Compra de equipamentos
   - Tile de NPC → Diálogo / Lore
 ↓
XP & Level Up → Evolução do Personagem
 ↓
Tile de Boss → Terminal of Vortex
```

## 📜 **Licença**

Este projeto está sob a licença **MIT**. Sinta-se à vontade para usar, estudar e modificar.

## 🧑‍💻 Autor

**Piter Gomes** — Aluno de Ciências da Computação (6º Semestre) & Desenvolvedor Full-Stack

📧 [Email](mailto:piterg.bio@gmail.com) | 💼 [LinkedIn](https://www.linkedin.com/in/piter-gomes-4a39281a1/) | 💻 [GitHub](https://github.com/pitercoding) | 🌐 [Portfolio](https://portfolio-pitergomes.vercel.app/)
