import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

public class GeradoraDeFigurinhas {

    public void cria(InputStream inputStream, String NomeArquivo) throws Exception{
        // leitura da imagem
        BufferedImage imagemOriginal = ImageIO.read(inputStream);

        // cria nova imagem em memória com transparência e com tamanho novo
        int largura = imagemOriginal.getWidth();
        int altura = imagemOriginal.getHeight();
        int novaLargura = largura - 187;
        int novaAltura = altura + 200;
        BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);

        // copiar a imagem original para nova imagem(em memória)
        Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
        graphics.drawImage(imagemOriginal, 0, 0, null);

        //configurar a fonte
        var fonte = new Font("Impact", Font.BOLD, 80);
        graphics.setColor(Color.RED);
        graphics.setFont(fonte);

        // escrever uma frase na nova imagem
        String texto = "TOPZERA";
        //graphics.drawString(texto, novaLargura/2, novaAltura - 100);

        // contorno do texto
        AffineTransform transform = graphics.getTransform();
        transform.translate(novaLargura/2, novaAltura - 100);
        graphics.transform(transform);
        FontRenderContext frc = graphics.getFontRenderContext();
        TextLayout tl = new TextLayout(texto, graphics.getFont().deriveFont(45F), frc);
        Shape shape = tl.getOutline(null);
        graphics.setStroke(new BasicStroke(5f));
        graphics.draw(shape);
        graphics.setColor(Color.YELLOW);
        graphics.fill(shape);

        // criar diretorio para as imagens
        File diretorio = new File("C:/Users/saulo/IdeaProjects/AluraStickers/images/");

        if (!diretorio.exists()) {
            diretorio.mkdir();
            System.out.println("Diretorio criado com sucesso");
        }

        // escrever a nova imagem em um arquivo
        ImageIO.write(novaImagem, "png", new File(diretorio, NomeArquivo));
    }

}
