package by.menko.finalproject.service.impl;

import by.menko.finalproject.dao.ComplaintDao;

import by.menko.finalproject.entity.Complaint;
import by.menko.finalproject.entity.enumtype.TypeServiceAndDao;
import by.menko.finalproject.exception.PersonalException;
import by.menko.finalproject.service.ComplaintService;

public class ComplaintServiceImpl extends ServiceImpl implements ComplaintService {
    private final static String REGEX_SENTENCE = "^(?!\\s\\t\\n\\r*$)[A-zА-яЁё0-9\\S_\\t\\n\\r ]*$";

    @Override
    public void addComplaint(String idDisk, String idUser, String complaintText, Integer idUserWasComplaint) throws PersonalException {
        ComplaintDao dao = transaction.createDao(TypeServiceAndDao.COMPLAINT);
        Complaint complaint = new Complaint();
        try {
            Integer userId = Integer.parseInt(idUser);
            Integer diskId = Integer.parseInt(idDisk);
            if (complaintText != null && !complaintText.isEmpty() && complaintText.matches(REGEX_SENTENCE)) {
                complaint.setTextComplaint(complaintText);
                complaint.setIdDisk(diskId);
                complaint.setUserIdComplained(userId);
                complaint.setUserWasComplained(idUserWasComplaint);
                dao.create(complaint);
            } else {
                throw new PersonalException();
            }
        } catch (NumberFormatException e) {
            throw new PersonalException();
        }
    }
}
