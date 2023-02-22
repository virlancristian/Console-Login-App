import java.io.*;

public class Database {
    File data;

    public Database() {
        data = new File("data.txt");

        if(!data.exists()) {
            try {
                data.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void writeAccount(String username, String password) {
        try {
            FileWriter fileWriter = new FileWriter(data, true);

            fileWriter.write(username + "\n");
            fileWriter.write(password + "\n");
            fileWriter.write("\n");

            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean verifyData(String usernameInput, String passwordInput) {
        String username, password;

        try {
            BufferedReader buffer = new BufferedReader(new FileReader(data));

            while((username = buffer.readLine()) != null) {
                password = buffer.readLine();
                buffer.readLine();


                if(username.equals(usernameInput) && password.equals(passwordInput)) {
                    return true;
                }
            }
        } catch(IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean isDatabaseEmpty() {
        return data.length() == 0;
    }
}
