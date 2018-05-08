import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.FileTime;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.stream.Stream;

public class FileManagement {

	public static void main(String[] args) throws IOException, ClassNotFoundException,FileNotFoundException {
		do {
			System.out.println(
					"1.List	all	the	files	in	the	folder\n2.Create	or	open	file	for	editing\n3.Display	the	content	of	a	perticular	file\nSelect	any	options\n4.Display	edit	history	of	a	file\n5.Exit");
			String directoryPath = "C:\\Users\\hp\\Desktop\\Myfile";
			Scanner in = new Scanner(System.in);
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			int options = in.nextInt();
			in.close();
			switch (options) {
			case 1:
				ListAllFiles(directoryPath);
				break;
			case 2:
				System.out.println("Enter	the	name	of	the	file	to	create	or	open	for	editing");
				try {

					String editFileName = reader.readLine();
					OpenForEditFile(directoryPath, editFileName);
				} catch (IOException e) {

					e.printStackTrace();
				}
				break;
			case 3:
				System.out.println("Enter	the	name	of	the	file	to	view	the	content");

				String viewFileName = reader.readLine();
				OpenForReadFile(directoryPath, viewFileName);
				break;
			case 4:
				System.out.println("Enter	the	name	of	the	file	to	view	the	content");

				String fileName = reader.readLine();
				DisplayLogFile(directoryPath, fileName);
				break;
			case 5:
				return;

			}
		} while (true);

	}

	@SuppressWarnings("unchecked")
	private static void DisplayLogFile(String directoryPath, String fileName)
			throws IOException, ClassNotFoundException {
		FileInputStream fis = new FileInputStream("C:\\Users\\hp\\Desktop\\Myfile\\log.txt");
		ObjectInputStream ois = new ObjectInputStream(fis);

		ArrayList<LogClass> resultArrayList = (ArrayList<LogClass>) ois.readObject();
		System.out
				.println("Modified	date	and	time\t\t" + "Added	content\t\t" + "size	of	the	filein	bytes\t\t");
		for (LogClass resultobj : resultArrayList) {
			if (resultobj.getFileName().equalsIgnoreCase(fileName)) {
				System.out.println(resultobj.getDateTime() + "\t\t\t" + resultobj.getEditContent() + "\t\t\t\t"
						+ resultobj.getSize());
			}
		}
		ois.close();
	}

	public static void ListAllFiles(String directoryPath) {
		try (Stream<Path> paths = Files.walk(Paths.get(directoryPath))) {
			paths.filter(Files::isRegularFile).forEach(System.out::println);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void OpenForEditFile(String directoryPath, String fileName) throws IOException {
		String path = directoryPath + "\\";
		Path filePath = Paths.get(path + fileName);
		ArrayList<LogClass> logArrayList = new ArrayList<LogClass>();
		FileOutputStream fos = new FileOutputStream("C:\\Users\\hp\\Desktop\\Myfile\\log.txt");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		if (Files.exists(filePath)) {
			System.out.println("File	already	existing....Ready	for	edit");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			String editContent = reader.readLine();
			Files.write(filePath, editContent.getBytes(), StandardOpenOption.APPEND);
			FileTime modifiedTime = Files.getLastModifiedTime(filePath, LinkOption.NOFOLLOW_LINKS);
			String modifyTime = format(modifiedTime.toMillis());

			LogClass object = new LogClass(fileName, modifyTime, editContent, FileChannel.open(filePath).size());
			logArrayList.add(object);
			oos.writeObject(logArrayList);

		} else {
			System.out.println("File	not	existing....Creating	new	file...Ready	for	edit");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			String editContent = reader.readLine();
			Files.write(filePath, editContent.getBytes());
			FileTime modifiedTime = Files.getLastModifiedTime(filePath, LinkOption.NOFOLLOW_LINKS);
			String modifyTime = format(modifiedTime.toMillis());

			LogClass object = new LogClass(fileName, modifyTime, editContent, FileChannel.open(filePath).size());
			logArrayList.add(object);
			oos.writeObject(logArrayList);

		}

		oos.close();
	}

	private static String format(long time) {
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy	HH-mm-ss");

		return dateFormat.format(new Date(time));
	}

	private static void OpenForReadFile(String directoryPath, String fileName) {
		String fPath = directoryPath + "\\";
		Path filePaths = Paths.get(fPath + fileName);
		if (Files.exists(filePaths)) {
			try (Stream<String> stream = Files.lines(filePaths)) {
				stream.forEachOrdered(System.out::println);

			} catch (IOException e) {

				e.printStackTrace();
			}
		} else {
			System.out.println("File	not	exist....");
		}
	}

}
