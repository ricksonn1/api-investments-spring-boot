# INVESTMENT API
 
Esta é uma API de investimentos que permite o gerenciamento de empresas, dividendos e relatórios. Ela utiliza tecnologias como Spring Boot, JWT, JPA e MySQL. Abaixo estão listados os endpoints disponíveis:

## Authentication
 
### /auth/login [POST]

Este endpoint é responsável por autenticar o usuário e gerar um token JWT válido. Ele recebe as credenciais do usuário (e-mail e senha) e retorna um token JWT que deve ser usado nas requisições subsequentes.

**Parâmetros de entrada:** 
- E-mail: String (obrigatório)
- Password: String (obrigatório)

**Respostas:**
- 200 OK - Token JWT válido gerado com sucesso
- 401 Unauthorized - Credenciais inválidas

## Enterprises
 
 ### /enterprises [GET]

Este endpoint é responsável por retornar todas as empresas cadastradas no sistema.

**Respostas:**
- 200 OK - Lista de empresas retornada com sucesso

### /enterprises [POST] 

Este endpoint é responsável por cadastrar uma nova empresa no sistema.

**Parâmetros de entrada:**
- Nome: String (obrigatório)
- Descrição: String (opcional)
- Setor: String (obrigatório)

**Respostas:**
- 201 Created - Empresa cadastrada com sucesso
- 400 Bad Request - Parâmetros inválidos

### /enterprises/{id} [GET] 

Este endpoint é responsável por retornar os detalhes de uma empresa específica.

**Parâmetros de entrada:**
- ID: Long (obrigatório)

**Respostas:**
- 200 OK - Detalhes da empresa retornados com sucesso
- 404 Not Found - Empresa não encontrada

### /enterprises/{id} [PUT] 

Este endpoint é responsável por atualizar os dados de uma empresa específica.

**Parâmetros de entrada:**
- ID: Long (obrigatório)
- Nome: String (opcional)
- Descrição: String (opcional)
- Setor: String (opcional)

**Respostas:**
- 200 OK - Empresa atualizada com sucesso
- 400 Bad Request - Parâmetros inválidos
- 404 Not Found - Empresa não encontrada

### /enterprises/{id} [DELETE] 

Este endpoint é responsável por excluir uma empresa específica.

**Parâmetros de entrada:**
- ID: Long (obrigatório)

**Respostas:**
- 204 No Content - Empresa excluída com sucesso
- 404 Not Found - Empresa não encontrada


## Dividends
 
### /enterprises/{id}/dividends [GET]

Este endpoint é responsável por retornar todos os dividendos de uma empresa específica.

**Parâmetros de entrada:**
- ID: Long (obrigatório)

**Respostas:**
- 200 OK - Lista de dividendos retornada com sucesso
- 404 Not Found - Empresa não encontrada

### /enterprises/{id}/dividends [POST] 

Este endpoint é responsável por cadastrar um novo dividendo para uma empresa específica.

**Parâmetros de entrada:**
- ID: Long (obrigatório)
- Data: Date (obrigatório)
- Valor: Double (obrigatório)

**Respostas:**
- 201 Created - Dividendo cadastrado com sucesso
- 400 Bad Request - Parâmetros inválidos
- 404 Not Found - Empresa não encontrada

### /enterprises/{id}/dividends/{date} [GET] 

Este endpoint é responsável por retornar os detalhes de um dividendo específico de uma empresa específica

### /enterprise/{id}/dividends/todas-as-datas [GET]
 
Este endpoint é responsável por retornar todos os dividendos de uma empresa específica em todas as datas.

**Parâmetros de entrada:**
- ID: Long (obrigatório)

**Respostas:**
- 200 OK - Lista de todos os dividendos da empresa retornada com sucesso
- 404 Not Found - Empresa não encontrada


