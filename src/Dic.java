import java.util.*;
import java.util.Map.Entry;
import java.io.*;

public class Dic {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	HashMap<String, LinkedList> dictionary = new HashMap<String, LinkedList>();
	HashMap<Integer, String> docId = new HashMap<Integer, String>();
	HashMap<String, Integer> freq = new HashMap<String, Integer>();

	// 전체 단어 카운트
	int termCount = 0;

	public void dic() throws IOException {
		File Dir = new File("DataSet");
		File[] fileList = Dir.listFiles();
		try {
			StringTokenizer st;
			int docid = 0;
			// 폴더 내의 모든 파일을 읽는 for조건문
			for (int i = 0; i < fileList.length; i++) {
				File file = fileList[i];
				FileReader fr = new FileReader(file);
				BufferedReader br = new BufferedReader(fr);
				String line = br.readLine();
				StringBuilder result = new StringBuilder();
				String result1 = "";
				String docno = "";
				int count = 0;
				int init = 0;

				// 파일 앞뒤의 태그를 제거하고 단어를 분석해 dictionary에 추가하는 parsing메서드를 호출하는 while문이다.  
				while (line != null) {
					if (line.startsWith("<DOCNO>")) {
						docid++;
						init = docid;
						line = line.substring(7, line.length() - 8);
						docno = line;
						line = line.toLowerCase();
						result.append("Document : " + docno + "\n");
						br.readLine();
					} else if (line.startsWith("<DOC>")) {
						result.append("Documnet : " + docno + "\n");
						docid++;
					} else if (line.startsWith("<DATE>")) {
						line = line.substring(6, line.length() - 7);
						parsing(line, docid);
						result.append("Date     : " + line + "\n");
					} else if (line.startsWith("<AUTHOR>")) {
						line = line.substring(8, line.length() - 9);
						result.append("Author   : " + line + "\n");
						line = line.toLowerCase();
						parsing(line, docid);
					} else if (line.startsWith("<TEXT>")) {
						line = line.substring(6, line.length() - 7).toString();
						result.append("Text     : " + line + "\n");
						line = line.toLowerCase();
						parsing(line, docid);
					} else if (line.startsWith("<FAVORITE>")) {
						line = line.substring(10, line.length() - 11).toString();
						result.append("Favorite : " + line + "\n");
						line = line.toLowerCase();
						parsing(line, docid);
					} else if (line.startsWith("</DOC>")) {
						// 문서가 끝났으므로 docId에 문서의 본문을 저장한다. 
						docId.put(docid, result.toString().substring(0, result.length() - 1));
						result.setLength(0);
					}
					line = br.readLine();
					if (line == null) {
						for (int j = init; j <= docid; j++) {
							// 한 파일에 있는 모든 문서의 documentnumber를 dictionary에 추가한다.  
							parsing(docno, i);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 빈도의 최대값과 최소값을 찾는 함수이다.
		HashMap.Entry<String, Integer> maxEntry = null;
		HashMap.Entry<String, Integer> minEntry = null;

		for (HashMap.Entry<String, Integer> entry : freq.entrySet()) {
			if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0) {
				maxEntry = entry;
			}
			if (minEntry == null || entry.getValue().compareTo(minEntry.getValue()) < 0) {
				minEntry = entry;
			}
		}
		
		// 검색을 끝내고 통계를 내는 함수이다.
		System.out.println("전체 단어의 수 : " + termCount + " 개");
		System.out.println("총 문서의 수 : " + docId.size() + " 개");
		System.out.println("문서 당 평균 단어 수 : " + Math.round((double) termCount / docId.size() * 100) / 100.0 + " 개");
		System.out.println("문서에 포함된 단어 수 중 가장 큰 수 : " + maxEntry.getValue() + " 개");
		System.out.println("문서에 포함된 단어 수 중 가장 작은 수 : " + minEntry.getValue() + " 개");
		System.out.println();

		// 질의를 받는 함수이다.
		while (true) {
			try {
			System.out.print(
					"********질의기*************\n*************************\n* 특정단어 찾기  : 1 *\n* 문서 본문 출력하기  : 2 *\n* 그만하기  : 3 *\n*************************\n입력 : ");

			int input = Integer.parseInt(br.readLine());
			// 특정 단어 찾기
			if (input == 1) {
				System.out.print("단어 입력 : ");
				String tempTerm = br.readLine();
				if ((dictionary.get(tempTerm) == null))
					System.out.println("None");
				else
					System.out.println(dictionary.get(tempTerm));
				// 문서 본문 출력
			} else if (input == 2) {
				System.out.print("문서 번호 입력 : ");
				int t = Integer.parseInt(br.readLine());
				if (docId.get(t) == null)
					System.out.println("None");
				else
					System.out.println(docId.get(t));
				// 그만하기
			} else if (input == 3)
				break;
			else 
				System.out.println("잘못된 번호입니다.");
			System.out.println();
			}
			catch (Exception e){
				e.getStackTrace();
			}
		}
	}

	
	// term과 docid를 출력받아 dictionary에 추가하는 메서드이다.
	public void parsing(String line, int docid) {
		StringTokenizer st = new StringTokenizer(line);

		while (st.hasMoreTokens() == true) {
			String temp = st.nextToken();
			termCount++;
			
			// 앞뒤의 불필요한 문자들을 제거한다. 
			while (temp.endsWith(".") || temp.endsWith(",") || temp.endsWith("\"") || temp.endsWith("\'")
					|| temp.endsWith("(") || temp.endsWith(")"))
				temp = temp.substring(0, temp.length() - 1);
			while (temp.startsWith(".") || temp.startsWith(",") || temp.startsWith("\'") || temp.startsWith("\"")
					|| temp.startsWith("(") || temp.startsWith(")"))
				temp = temp.substring(1, temp.length());

			// 이미 term을 dictionary에 추가한 경우
			if (dictionary.containsKey(temp)) {
				// 단어의 빈도수를 추가한다.
				int frequency = freq.get(temp).intValue();
				freq.replace(temp, ++frequency);
				// postings list만 추가해준다.
				dictionary.get(temp).add(docid);
				
				// 단어가 dictionary에 없는 경우
			} else if (dictionary.containsKey(temp) == false) {
				freq.put(temp, 1);
				add(temp, docid);
			}
		}
	}

	// postingslist에 최초의 term과 docid를 추가하는 메서드이다.
	public void add(String temp, int docid) {
		LinkedList<Integer> list = new LinkedList<Integer>();
		list.add(docid);
		dictionary.put(temp, list);
	}

}
