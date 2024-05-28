<?php
// Configurações do banco de dados
$host = 'localhost'; // Endereço do servidor do PostgreSQL
$port = '5432'; // Porta do PostgreSQL (padrão: 5432)
$dbname = 'locadoracarro'; // Nome do banco de dados
$user = 'postgres'; // Nome de usuário do PostgreSQL
$password = 'postgres'; // Senha do PostgreSQL

// Dados do formulário
$nome_completo = $_POST['nome'] ?? '';
$modelo_carro = $_POST['modelo_carro'] ?? '';
$telefone = $_POST['telefone'] ?? '';
$cpf = $_POST['cpf'] ?? '';
$email = $_POST['email'] ?? '';
$local_retirada = $_POST['local_retirada'] ?? '';
$data_retirada = $_POST['data_retirada'] ?? '';
$data_devolucao = $_POST['data_devolucao'] ?? '';

try {
    // Conexão com o banco de dados
    $conn = new PDO("pgsql:host=$host;port=$port;dbname=$dbname;user=$user;password=$password");
    $conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
    
    // Preparar e executar a query de inserção
    $stmt = $conn->prepare("INSERT INTO reservas (nome_completo, modelo_carro, telefone, cpf, email, local_retirada, data_retirada, data_devolucao) VALUES (:nome_completo, :modelo_carro, :telefone, :cpf, :email, :local_retirada, :data_retirada, :data_devolucao)");
    $stmt->bindParam(':nome_completo', $nome_completo);
    $stmt->bindParam(':modelo_carro', $modelo_carro);
    $stmt->bindParam(':telefone', $telefone);
    $stmt->bindParam(':cpf', $cpf);
    $stmt->bindParam(':email', $email);
    $stmt->bindParam(':local_retirada', $local_retirada);
    $stmt->bindParam(':data_retirada', $data_retirada);
    $stmt->bindParam(':data_devolucao', $data_devolucao);
    
    // Verificar se todos os campos foram preenchidos
    if (!empty($nome_completo) && !empty($modelo_carro) && !empty($telefone) && !empty($cpf) && !empty($email) && !empty($local_retirada) && !empty($data_retirada) && !empty($data_devolucao)) {
        // Executar a query de inserção
        $stmt->execute();
        echo "Reserva realizada com sucesso!";
    } else {
        echo "Todos os campos são obrigatórios!";
    }
} catch(PDOException $e) {
    echo "Erro ao inserir dados: " . $e->getMessage();
}
?>
