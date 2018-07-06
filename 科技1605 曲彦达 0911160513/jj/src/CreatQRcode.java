
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import com.swetake.util.Qrcode;
public class CreatQRcode {

public static void main(String[] args)throws Exception {
int imageSize=135;
// TODO Auto-generated method stub
Qrcode qrcode=new Qrcode();
String content="http://www.dijiaxueshe.com";
byte[] data=content.getBytes("utf-8");
qrcode.setQrcodeErrorCorrect('H');
qrcode.setQrcodeEncodeMode('B');
boolean[][] qrdata=qrcode.calQrcode(data);
//…Ë÷√Õº∆¨ª∫≥Â
int v=6;
//int width=67+12*(v-1);
//int height=67+12*(v-1);
qrcode.setQrcodeVersion(v);
BufferedImage bufferedImage=new BufferedImage(imageSize,imageSize,BufferedImage.TYPE_INT_RGB);
Graphics2D gs=bufferedImage.createGraphics();
//…Ë÷√±≥æ∞…´
gs.setBackground(Color.white);
//«Â≥˝ª≠≤º
gs.clearRect(0, 0,imageSize, imageSize);
int StartR=255,StartG=0,StartB=0;
int endR=0,endG=0,endB=255;
//∂˛Œ¨¬ÎªÊª≠
int p=3;
for(int i=0;i<qrdata.length;i++){
for(int j=0;j<qrdata.length;j++){
if(qrdata[i][j]){
Random rand=new Random();
int num1=StartR+(endR-StartR)*(j+1)/qrdata.length;
int num2=StartG+(endG-StartG)*(i+1)/qrdata.length;
int num3=StartB+(endB-StartB)*(i+1)/qrdata.length;
Color color=new Color(num1,num2,num3);
gs.setColor(color);
gs.fillRect(i*4+p,j*4+p, 4, 4);
}
}
}
BufferedImage logo=scale("D:/logo.jpg",60,60,true);
int logoSize=imageSize/4;
int o=(imageSize-logoSize)/2;
gs.drawImage(logo, o, o, logoSize, logoSize, null);
gs.dispose();
bufferedImage.flush();
try{
ImageIO.write(bufferedImage, "png", new File("D:/qrcode.png"));
}catch(IOException e){
e.printStackTrace();
System.out.println(" ß∞‹");
}
System.out.println("≥…π¶¡À");
}

private static BufferedImage scale(String logopath, int width, int height, boolean hasFiller) throws Exception {
double radio=0.0;
BufferedImage logo=ImageIO.read(new File(logopath));
Image destImage=logo.getScaledInstance(width, height, BufferedImage.SCALE_SMOOTH);
if(logo.getHeight()>height || logo.getWidth()>width){
if(logo.getHeight()>logo.getWidth()){
radio=new Integer(height).doubleValue()/logo.getHeight();
}else{
radio=new Integer(width).doubleValue()/logo.getWidth();
}
AffineTransformOp op=new AffineTransformOp(AffineTransform.getScaleInstance(radio, radio),null);
destImage=op.filter(logo, null);
}
  if(hasFiller){
  BufferedImage image=new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
  Graphics2D gs=image.createGraphics();
  gs.setColor(Color.WHITE);
  gs.clearRect(0, 0, width, height);
  if(width==destImage.getHeight(null)){
  gs.drawImage(destImage,0,(height-destImage.getHeight(null))/2,destImage.getWidth(null),destImage.getHeight(null), Color.WHITE,null);
  }else{
  gs.drawImage(destImage,0,(width-destImage.getWidth(null))/2,destImage.getWidth(null),destImage.getHeight(null), Color.WHITE,null);
  }
  gs.dispose();
  destImage=image;
}
       return (BufferedImage) destImage;
}
}