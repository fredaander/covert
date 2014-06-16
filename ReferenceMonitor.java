
import java.util.*;

public class ReferenceMonitor {

	public static ArrayList<Object> ObjList = new ArrayList<Object>();
	public static ArrayList<Subject> SubList = new ArrayList<Subject>();
	public static class ObjectManager 
	{
		public static void objectRead (Subject sub, int val)
		{
			sub.setValue(val);
		}
		public static void objectWrite (Object obj, int val)
		{
			obj.setValue(val);
		}
	}
	public static void processRead (InstructionObject io)
	{
		Subject temp1 = null;
		Object temp2 = null;
		int objseclevel = 0;
		int subseclevel = 0;
		int objvalue =0;
		String command = io.getCommand();
		String objname = io.getObjectName();
		String subname = io.getSubjectName();
		
		ListIterator<Subject> SubIterator = SubList.listIterator();
		Boolean foundsub = false;
		while (SubIterator.hasNext())
		{
			temp1= SubIterator.next();
			if (temp1.getName().equals(subname))
			{
				foundsub = true;
				subseclevel = temp1.getSeclevel();
				break;
			}	
		}
		ListIterator<Object> ObjIterator = ObjList.listIterator();
		Boolean foundobj = false;
		while (ObjIterator.hasNext())
		{
			temp2 = ObjIterator.next();
			if (temp2.getName().equals(objname))
			{
				foundobj = true;
				objseclevel = temp2.getSeclevel();
				break;
			}	
		}
		objvalue=temp2.getValue();
		
		if (!foundsub || !foundobj)
		{
			processBad();
		}	
		else if (foundsub && !foundobj)
		{
			//Send zero
			ObjectManager.objectRead(temp1, 0);
		}
		else if (!foundsub && foundobj)
		{
			processBad();
		}
		else
		{
			if (subseclevel>=objseclevel)
				ObjectManager.objectRead(temp1, objvalue);
			else
				ObjectManager.objectRead(temp1, 0);
		}
	}
	
	public static void processWrite (InstructionObject io)
	{
		Subject temp1 = null;
		Object temp2 = null;
		int objseclevel = 0;
		int subseclevel = 0;
		String objname = io.getObjectName();
		String subname = io.getSubjectName();
		int val = io.getValue();
		
		ListIterator<Subject> SubIterator = SubList.listIterator();
		Boolean foundsub = false;
		while (SubIterator.hasNext())
		{
			temp1= SubIterator.next();
			if (temp1.getName().equals(subname))
			{
				foundsub = true;
				subseclevel = temp1.getSeclevel();
				break;
			}	
		}
		ListIterator<Object> ObjIterator = ObjList.listIterator();
		Boolean foundobj = false;
		while (ObjIterator.hasNext())
		{
			temp2 = ObjIterator.next();
			if (temp2.getName().equals(objname))
			{
				foundobj = true;
				objseclevel = temp2.getSeclevel();
				break;
			}	
		}
		
		if (!foundsub || !foundobj)
		{
			processBad();
		}	
		else if (!foundsub && foundobj)
		{
			processBad();
		}
		else
		{
			
			if (subseclevel<=objseclevel)
				ObjectManager.objectWrite(temp2, val);
		}
	}
	public static void processBad()
	{
		System.out.println("Bad Instrustion");
		printState();
	}
	public static void addSubject(Subject sub)
	{
		SubList.add(sub);
	}
	public static void addObject(Object obj)
	{
		ObjList.add(obj);
	}
	public static void printState()
	{
		ListIterator<Subject> SubIterator = SubList.listIterator();
		Subject temps = null;
		Object tempo = null;
		while (SubIterator.hasNext())
		{
			temps=SubIterator.next();
			System.out.println(temps.getName()+" has recently read: "+temps.getValue());	
		}
		ListIterator<Object> ObjIterator = ObjList.listIterator();
		while (ObjIterator.hasNext())
		{
			tempo=ObjIterator.next();
			System.out.println(tempo.getName()+" has value: "+tempo.getValue());		
		}
		System.out.println(" ");
	}
}
