package login;

	import javax.crypto.Cipher;
	import javax.crypto.spec.IvParameterSpec;
	import javax.crypto.spec.SecretKeySpec;
	import static org.apache.commons.codec.binary.Base64.decodeBase64;
	import static org.apache.commons.codec.binary.Base64.encodeBase64;
	 
	public class StringEncrypt {
	 
	    // Definición del tipo de algoritmo a utilizar (AES, DES, RSA), mirar Cipher
	    private final static String alg = "AES";
	    // Definición del modo de cifrado a utilizar
	    private final static String cI = "AES/CBC/PKCS5Padding";
	 
	 

	    public String encrypt(String key, String cleartext) throws Exception {
	            String iv = "1478523690SLJHXV";
	    		Cipher cipher = Cipher.getInstance(cI);
	            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(), alg);
	            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv.getBytes());
	            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, ivParameterSpec);
	            byte[] encrypted = cipher.doFinal(cleartext.getBytes());
	            return new String(encodeBase64(encrypted));
	    }
	 
	  
	    public  String decrypt(String key, String encrypted) throws Exception {
            	String iv = "1478523690SLJHXV";
	    		Cipher cipher = Cipher.getInstance(cI);
	            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(), alg);
	            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv.getBytes());
	            byte[] enc = decodeBase64(encrypted);
	            cipher.init(Cipher.DECRYPT_MODE, skeySpec, ivParameterSpec);
	            byte[] decrypted = cipher.doFinal(enc);
	            return new String(decrypted);
	    }
	 
	
}
