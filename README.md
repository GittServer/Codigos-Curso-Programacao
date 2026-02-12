# C-digo-C-Curso
Código C# Curso

// Importa namespaces necessários para o programa
using System; // Namespace para tipos fundamentais do .NET
using System.Collections.Generic; // Namespace para coleções genéricas
using System.IO; // Namespace para operações de entrada e saída de arquivos
using System.Text.Json; // Namespace para serialização e desserialização JSON

// Classe que representa um produto
public class Produto
{
    // Propriedade para armazenar o ID do produto
    public int Id { get; set; }
    // Propriedade para armazenar o nome do produto
    public string Nome { get; set; }
    // Propriedade para armazenar a categoria do produto
    public string Categoria { get; set; }
    // Propriedade para armazenar o preço do produto
    public decimal Preco { get; set; }
    // Propriedade para armazenar a quantidade do produto
    public int Quantidade { get; set; }

    // Sobrescreve o método ToString para retornar uma string que representa o produto
    public override string ToString()
    {
        return $"Id: {Id}, Nome: {Nome}, Categoria: {Categoria}, Preco: {Preco}, Quantidade: {Quantidade}";
    }
}

// Classe que representa uma categoria
public class Categoria
{
    // Propriedade para armazenar o ID da categoria
    public int Id { get; set; }
    // Propriedade para armazenar o nome da categoria
    public string Nome { get; set; }

    // Sobrescreve o método ToString para retornar uma string que representa a categoria
    public override string ToString()
    {
        return $"Id: {Id}, Nome: {Nome}";
    }
}

// Classe que representa um fornecedor
public class Fornecedor
{
    // Propriedade para armazenar o ID do fornecedor
    public int Id { get; set; }
    // Propriedade para armazenar o nome do fornecedor
    public string Nome { get; set; }
    // Propriedade para armazenar o endereço do fornecedor
    public string Endereco { get; set; }

    // Sobrescreve o método ToString para retornar uma string que representa o fornecedor
    public override string ToString()
    {
        return $"Id: {Id}, Nome: {Nome}, Endereco: {Endereco}";
    }
}

// Classe que representa o estoque
public class Estoque
{
    // Lista de produtos no estoque
    public List<Produto> Produtos { get; set; } = new List<Produto>();

    // Método para adicionar um produto ao estoque
    public void AdicionarProduto(Produto produto)
    {
        Produtos.Add(produto); // Adiciona o produto à lista
        SalvarDados(); // Salva os dados no arquivo JSON
    }

    // Método para remover um produto do estoque
    public void RemoverProduto(int id)
    {
        Produtos.RemoveAll(p => p.Id == id); // Remove o produto com o ID especificado
        SalvarDados(); // Salva os dados no arquivo JSON
    }

    // Método para consultar um produto no estoque
    public Produto ConsultarProduto(int id)
    {
        return Produtos.Find(p => p.Id == id); // Retorna o produto com o ID especificado
    }

    // Método privado para salvar os dados no arquivo JSON
    private void SalvarDados()
    {
        string json = JsonSerializer.Serialize(Produtos, new JsonSerializerOptions { WriteIndented = true }); // Serializa a lista de produtos para JSON
        File.WriteAllText("estoque.json", json); // Escreve o JSON no arquivo
    }

    // Método para carregar os dados do arquivo JSON
    public void CarregarDados()
    {
        if (File.Exists("estoque.json")) // Verifica se o arquivo existe
        {
            string json = File.ReadAllText("estoque.json"); // Lê o conteúdo do arquivo
            Produtos = JsonSerializer.Deserialize<List<Produto>>(json); // Desserializa o JSON para a lista de produtos
        }
    }
}

// Classe principal do programa
class Program
{
    // Método principal do programa
    static void Main(string[] args)
    {
        Estoque estoque = new Estoque(); // Cria um novo estoque
        estoque.CarregarDados(); // Carrega os dados do arquivo JSON

        while (true) // Loop infinito
        {
            Console.WriteLine("1. Adicionar Produto"); // Exibe as opções
            Console.WriteLine("2. Remover Produto");
            Console.WriteLine("3. Consultar Produto");
            Console.WriteLine("4. Sair");

            int opcao = Convert.ToInt32(Console.ReadLine()); // Lê a opção do usuário

            switch (opcao) // Switch para processar a opção
            {
                case 1:
                    Produto produto = new Produto(); // Cria um novo produto
                    Console.Write("Id: "); // Lê o ID do produto
                    produto.Id = Convert.ToInt32(Console.ReadLine());
                    Console.Write("Nome: "); // Lê o nome do produto
                    produto.Nome = Console.ReadLine();
                    Console.Write("Categoria: "); // Lê a categoria do produto
                    produto.Categoria = Console.ReadLine();
                    Console.Write("Preco: "); // Lê o preço do produto
                    produto.Preco = Convert.ToDecimal(Console.ReadLine());
                    Console.Write("Quantidade: "); // Lê a quantidade do produto
                    produto.Quantidade = Convert.ToInt32(Console.ReadLine());
                    estoque.AdicionarProduto(produto); // Adiciona o produto ao estoque
                    break;
                case 2:
                    Console.Write("Id do produto a remover: "); // Lê o ID do produto a remover
                    int idRemover = Convert.ToInt32(Console.ReadLine());
                    estoque.RemoverProduto(idRemover); // Remove o produto do estoque
                    break;
                case 3:
                    Console.Write("Id do produto a consultar: "); // Lê o ID do produto a consultar
                    int idConsultar = Convert.ToInt32(Console.ReadLine());
                    Produto p = estoque.ConsultarProduto(idConsultar); // Consulta o produto no estoque
                    if (p != null) // Se o produto for encontrado
                        Console.WriteLine(p.ToString()); // Exibe o produto
                    else
                        Console.WriteLine("Produto não encontrado."); // Exibe mensagem de erro
                    break;
                case 4:
                    return; // Sai do programa
            }
        }
    }
}
// FIM CODE.
