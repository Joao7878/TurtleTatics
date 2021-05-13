import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
public class App {
    public static void main(String[] args) throws Exception {
        FileWriter arqW = new FileWriter("C:\\Users\\arcan\\Documents\\Java\\Teste\\info.txt");
        PrintWriter gravadorArq = new PrintWriter(arqW);
        gravadorArq.println("Tchan");
        gravadorArq.println("Couraca,Pocao");
        arqW.close();

        String linha, itens[]=new String[5];
        FileReader arqR = new FileReader("C:\\Users\\arcan\\Documents\\Java\\Teste\\info.txt");
        BufferedReader leitorArq = new BufferedReader(arqR);
        linha=leitorArq.readLine();
        int i;
        while(linha!=null){
            if(linha=="Juao"){
                linha=leitorArq.readLine();
                itens=linha.split(",");
                for(i=0;i<itens.length;i++){

                }
            }
            linha=leitorArq.readLine();
        }
        System.out.println("Jogadores: "+ leitorArq.readLine());
    }
}
