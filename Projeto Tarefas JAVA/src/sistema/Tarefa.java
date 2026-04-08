package sistema;

// Classe que representa uma tarefa
public class Tarefa {

    String descricao; // descrição da tarefa
    String data; // data de vencimento
    boolean concluida; // status da tarefa

    // Construtor da tarefa
    public Tarefa(String descricao, String data) {
        this.descricao = descricao; // recebe descrição
        this.data = data; // recebe data
        this.concluida = false; // inicia como não concluída
    }

    // Método para marcar como concluída
    public void concluir() {
        this.concluida = true; // muda status para concluída
    }

    // Método para exibir tarefa no JList
    @Override
    public String toString() {
        return descricao + " | " + data + " | " + (concluida ? "✔" : "Pendente");
    }
}
// FIM CODE.