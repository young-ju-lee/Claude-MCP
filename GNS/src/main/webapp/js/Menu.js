/**************************************************************************
*	�޴����� ��ũ �� Location ����
**************************************************************************/
//document.domain = "";

var	baseUrl = "http://localhost:7000";
var ssl_domain = "http://localhost:7000";


//var	baseUrl = "http://localhost:90";
//var ssl_domain = "https://localhost:443";

var menuArr = new Array();
var m = 0; 

function initMenuData(){
// Home
menuArr[m++] = new Array('00', 'Main',baseUrl+'/index.jsp', '');


// [ ] About KTDS
menuArr[m++] = new Array('01', 'About KTDS',baseUrl+'/company/about_intro.jsp', '');

menuArr[m++] = new Array('0101', '�������',baseUrl+'/company/about_intro.jsp', '');
menuArr[m++] = new Array('010101', 'ȸ�簳��',baseUrl+'/company/about_intro.jsp', '');
menuArr[m++] = new Array('010102', '����',baseUrl+'/company/about_history.jsp', '');
menuArr[m++] = new Array('010103', '����',baseUrl+'/company/about_vision.jsp', '');
menuArr[m++] = new Array('010104', '�濵��Ģ',baseUrl+'/company/about_principle.jsp', '');
menuArr[m++] = new Array('010105', 'CI',baseUrl+'/company/about_ci.jsp', '');
menuArr[m++] = new Array('010106', '�������Ȳ',baseUrl+'/company/about_place.jsp', '');
menuArr[m++] = new Array('010107', '���ô±�',baseUrl+'/company/about_location.jsp', '');

menuArr[m++] = new Array('0102', '�����濵',baseUrl+'/company/open_inno_01.jsp', '');
menuArr[m++] = new Array('010201', '�濵����',baseUrl+'/company/open_inno_01.jsp', '');
menuArr[m++] = new Array('01020101', '�濵��ǥ',baseUrl+'/company/open_inno_01.jsp', '');
menuArr[m++] = new Array('01020102', '�ٽɱ��',baseUrl+'/company/open_inno_02.jsp', '');
menuArr[m++] = new Array('01020103', '�η��ڿ�',baseUrl+'/company/open_inno_03.jsp', '');
menuArr[m++] = new Array('01020104', 'ǰ������',baseUrl+'/company/open_inno_04.jsp', '');
menuArr[m++] = new Array('01020105', 'R&amp;D',baseUrl+'/company/open_inno_05.jsp', '');
menuArr[m++] = new Array('01020106', '��������',baseUrl+'/company/open_inno_06.jsp', '');
menuArr[m++] = new Array('010202', '�����濵',baseUrl+'/company/open_ethics_01.jsp', '');
menuArr[m++] = new Array('01020201', '��������',baseUrl+'/company/open_ethics_01.jsp', '');
menuArr[m++] = new Array('01020202', '�����ϱ�',baseUrl+'/company/open_ethics_02.jsp', '');
menuArr[m++] = new Array('010203', '��ȸ����',baseUrl+'/company/open_social.jsp', '');

menuArr[m++] = new Array('0103', 'press',baseUrl+'/company/pr_news.jsp', '');
menuArr[m++] = new Array('010301', 'press',baseUrl+'/company/pr_news.jsp', '');
menuArr[m++] = new Array('010302', '����',baseUrl+'/company/pr_ad.jsp', '');
//menuArr[m++] = new Array('010303', '�系���',baseUrl+'/company/dsb_news.jsp', '');
menuArr[m++] = new Array('010303', '�������� ','/company/pr_notice.jsp', '');
menuArr[m++] = new Array('010305', '������� ',baseUrl+'/company/pr_active.jsp', '');

menuArr[m++] = new Array('0104', 'KTDS����',baseUrl+'/company/sym_ktdsis.jsp', '');
menuArr[m++] = new Array('010401', 'KTDS IS',baseUrl+'/company/sym_ktdsis.jsp', '');
menuArr[m++] = new Array('010402', 'KTDS Inside',baseUrl+'/company/sym_inside.jsp', '');


// [ ] IT Service
menuArr[m++] = new Array('02', 'IT Service',baseUrl+'/business/it_01.jsp', '');

menuArr[m++] = new Array('0201', '����о�',baseUrl+'/business/it_01.jsp', '');
menuArr[m++] = new Array('020101', '�������',baseUrl+'/business/it_01.jsp', '');
menuArr[m++] = new Array('020102', 'SM/SI',baseUrl+'/business/it_02_01.jsp', '');
menuArr[m++] = new Array('02010201', '����',baseUrl+'/business/it_02_01.jsp', '');
menuArr[m++] = new Array('02010202', 'SI',baseUrl+'/business/it_02_02.jsp', '');
menuArr[m++] = new Array('020103', 'ITO/BPO',baseUrl+'/business/it_03.jsp', '');
menuArr[m++] = new Array('020104', 'NI',baseUrl+'/business/it_04_01.jsp', '');
menuArr[m++] = new Array('02010401', '����',baseUrl+'/business/it_04_01.jsp', '');
menuArr[m++] = new Array('02010402', '���Ŵ���',baseUrl+'/business/it_04_02.jsp', '');
menuArr[m++] = new Array('020105', 'CONSULTING',baseUrl+'/business/it_05.jsp', '');
menuArr[m++] = new Array('020106', 'Ŭ���弭��','javascript:popCloud();', '');
menuArr[m++] = new Array('020107', '�ַ�� ',baseUrl+'/business/solution.jsp', '');

menuArr[m++] = new Array('0202', '����',baseUrl+'/business/service_01.jsp', '');
menuArr[m++] = new Array('020201', '���� ����',baseUrl+'/business/service_01.jsp', '');
menuArr[m++] = new Array('020202', 'AMO',baseUrl+'/business/service_02.jsp', '');
menuArr[m++] = new Array('020203', 'IMO',baseUrl+'/business/service_03.jsp', '');
menuArr[m++] = new Array('020204', 'FMO',baseUrl+'/business/service_04.jsp', '');
menuArr[m++] = new Array('020205', 'Print Shop',baseUrl+'/business/service_05.jsp', '');

menuArr[m++] = new Array('0203', '�ֿ����',baseUrl+'/business/sucess.jsp', '');
menuArr[m++] = new Array('020301', '�������',baseUrl+'/business/sucess.jsp', '');
menuArr[m++] = new Array('020302', '�������',baseUrl+'/business/sucess_01.jsp', '');
menuArr[m++] = new Array('02030201', 'ICIS',baseUrl+'/business/sucess_01.jsp', '');
menuArr[m++] = new Array('02030202', 'NeOSS',baseUrl+'/business/sucess_02.jsp', '');
menuArr[m++] = new Array('02030203', 'KT ERP',baseUrl+'/business/sucess_03.jsp', '');
menuArr[m++] = new Array('02030204', 'KT CRM',baseUrl+'/business/sucess_04.jsp', '');
menuArr[m++] = new Array('02030205', 'KT EDW',baseUrl+'/business/sucess_05.jsp', '');
menuArr[m++] = new Array('02030206', 'KT CCMS',baseUrl+'/business/sucess_06.jsp', '');
menuArr[m++] = new Array('02030207', 'N-STEP',baseUrl+'/business/sucess_07.jsp', '');

menuArr[m++] = new Array('0204', '������',baseUrl+'/business/sup_contact_01.jsp', '');
menuArr[m++] = new Array('020401', '������',baseUrl+'/business/sup_contact_01.jsp', '');
menuArr[m++] = new Array('02040101', '�����Ǿȳ�',baseUrl+'/business/sup_contact_01.jsp', '');
menuArr[m++] = new Array('02040102', '�����ϱ�',ssl_domain+'/business/sup_contact_02.jsp', '');
menuArr[m++] = new Array('020402', '��Ʈ�ʽ�',baseUrl+'/business/sup_partner_01.jsp', '');
menuArr[m++] = new Array('02040201', '�⺻����',baseUrl+'/business/sup_partner_01.jsp', '');
menuArr[m++] = new Array('02040202', 'Life Cycle',baseUrl+'/business/sup_partner_02.jsp', '');
menuArr[m++] = new Array('02040203', 'Eco Cluster',baseUrl+'/business/sup_partner_03.jsp', '');
menuArr[m++] = new Array('020403', '�������',baseUrl+'/business/sup_proposal_01.jsp', '');
menuArr[m++] = new Array('02040301', '������Ⱦȳ�',baseUrl+'/business/sup_proposal_01.jsp', '');
menuArr[m++] = new Array('02040302', '�����ϱ�',ssl_domain+'/business/sup_proposal_02.jsp', '');


// [ ] SMART People
menuArr[m++] = new Array('03', 'SMART People',baseUrl+'/people/ceo_greeting.jsp', '');

menuArr[m++] = new Array('0301', 'CEO',baseUrl+'/people/ceo_greeting.jsp', '');
menuArr[m++] = new Array('030101', '�λ縻',baseUrl+'/people/ceo_greeting.jsp', '');
menuArr[m++] = new Array('030102', '������',baseUrl+'/people/ceo_profile.jsp', '');
menuArr[m++] = new Array('030103', '�޽���',baseUrl+'/people/ceo_message.jsp', '');
menuArr[m++] = new Array('030104', '����ٹ�',baseUrl+'/people/ceo_photo.jsp', '');

menuArr[m++] = new Array('0302', '����Ʈ ����',baseUrl+'/people/smart_open.jsp', '');
menuArr[m++] = new Array('030201', 'IT ���� ��ũ',baseUrl+'/people/smart_open.jsp', '');
menuArr[m++] = new Array('030202', '���̳�&IT����',baseUrl+'/people/smart_seminar.jsp', '');

menuArr[m++] = new Array('0303', 'KTDS ������',baseUrl+'/people/life_story.jsp', '');

menuArr[m++] = new Array('030301', 'KTDS ���丮',baseUrl+'/people/life_story.jsp', '');
menuArr[m++] = new Array('030302', 'KTDS �����',baseUrl+'/people/life_people.jsp', '');
menuArr[m++] = new Array('030303', 'KTDS ��α�','javascript:onclick=void window.open("http://ktdsblog.com/");', '');

menuArr[m++] = new Array('0304', '����ä��',baseUrl+'/people/recruit_talent.jsp', '');
menuArr[m++] = new Array('030401', '�����',baseUrl+'/people/recruit_talent.jsp', '');
menuArr[m++] = new Array('030402', '�λ�����',baseUrl+'/people/recruit_person_01.jsp', '');
menuArr[m++] = new Array('03040201', '�����Ұ�',baseUrl+'/people/recruit_person_01.jsp', '');
menuArr[m++] = new Array('03040202', '����/�����Ļ�',baseUrl+'/people/recruit_person_02.jsp', '');
menuArr[m++] = new Array('03040203', '�λ���',baseUrl+'/people/recruit_person_03.jsp', '');
menuArr[m++] = new Array('03040204', '�°�����',baseUrl+'/people/recruit_person_04.jsp', '');
menuArr[m++] = new Array('030403', '��������',baseUrl+'/people/recruit_edu_01.jsp', '');
menuArr[m++] = new Array('03040301', 'HRD����Ʈ��',baseUrl+'/people/recruit_edu_01.jsp', '');
menuArr[m++] = new Array('03040302', '�űԻ���������α׷�',baseUrl+'/people/recruit_edu_02.jsp', '');
menuArr[m++] = new Array('03040303', 'QPC',baseUrl+'/people/recruit_edu_03.jsp', '');
menuArr[m++] = new Array('030404', '�Ի��������̵�',baseUrl+'/people/recruit_guide_02.jsp', '');
menuArr[m++] = new Array('03040401', 'ä��ȳ�',baseUrl+'/people/recruit_guide_01.jsp', '');
menuArr[m++] = new Array('03040402', 'ä�����',baseUrl+'/people/recruit_guide_02.jsp', '');
menuArr[m++] = new Array('03040403', 'ä�� FAQ',baseUrl+'/people/recruit_guide_03.jsp', '');


// [ ] ETC
menuArr[m++] = new Array('11', '����Ʈ��',baseUrl+'/etc/sitemap.jsp', '');
menuArr[m++] = new Array('12', 'KTDS RSS',baseUrl+'/etc/rss.jsp', '');
menuArr[m++] = new Array('1201', 'RSS ���� �ٿ�ε�',baseUrl+'/etc/rss_down.jsp', '');
menuArr[m++] = new Array('13', '���û���Ʈ',baseUrl+'/etc/site.jsp', '');
menuArr[m++] = new Array('14', '������α׷�','javascript:showDiv(\'ly_downloadViewer\', \'754\', \'520\');', '');
menuArr[m++] = new Array('15', '�̸������ܼ��� �ź�','javascript:showDiv(\'ly_noemail\', \'754\', \'270\');', '');
menuArr[m++] = new Array('16', '�������� ��޹�ħ','javascript:showDiv(\'ly_privacy\', \'754\', \'520\');', '');
menuArr[m++] = new Array('17', '�Ǹ�����','/etc/pop_certi.jsp', '');
menuArr[m++] = new Array('18', 'Life Plane',baseUrl+'/etc/lifeplan_main.jsp', '');
menuArr[m++] = new Array('1802', '�˸�',baseUrl+'/etc/lifeplan_01.jsp?num=1', '');
menuArr[m++] = new Array('1808', '������',baseUrl+'/etc/lifeplan_05.jsp?num=2', '');
menuArr[m++] = new Array('1803', '�����Խ���',baseUrl+'/etc/lifeplan_02.jsp?num=3', '');
menuArr[m++] = new Array('1804', '����ٹ�',baseUrl+'/etc/lifeplan_photo.jsp?num=4', '');
menuArr[m++] = new Array('1805', '�����ϱ�',baseUrl+'/etc/lifeplan_03_write.jsp?num=5', '');
menuArr[m++] = new Array('1806', 'KTDS Wish',baseUrl+'/etc/lifeplan_wish.jsp?num=6', '');
menuArr[m++] = new Array('1807', 'KTDS ���ã��',baseUrl+'/etc/lifeplan_search.jsp?num=7', '');

menuArr[m++] = new Array('1808', '������������',ssl_domain+'/etc/lifeplan_member_modify.jsp', '');
menuArr[m++] = new Array('1809', '��й�ȣ����',ssl_domain+'/etc/lifeplan_pw_modify.jsp', '');
menuArr[m++] = new Array('1810', '�α׾ƿ�',ssl_domain+'/etc/proc_lifeplan_logout.jsp', '');

menuArr[m++] = new Array('19', 'Smart Phone',baseUrl+'/etc/smartphone.jsp', '');


// [ ] ����
menuArr[m++] = new Array('99', 'English','javascript:go_english()', '');

}

/**************************************************************************
*	�޴� ��ũ
**************************************************************************/
function menuLink(str,num)
// Normal Link
{
    var isFind = false;
    for (i=0; i<str.length; i=i+2)
    {
        for (j=0; j<menuArr.length; j++)
        {
            if (str==menuArr[j][0])
            {
                if(menuArr[j][2]=="#")
                {
                    alert("�غ����Դϴ�.");
                    return;
                }
                else
                {
                    window.location.href=menuArr[j][3] + menuArr[j][2];
                    isFind = true;
                    break;
                }
            }
        }

        if(isFind) break;
    }
}


function menuLinkPop(str)
// Popup
{
    var isFind = false;
    for (i=0; i<str.length; i=i+2)
    {
        for (j=0; j<menuArr.length; j++)
        {
            if (str==menuArr[j][0])
            {
                if(menuArr[j][2]=="#")
                {
                    alert("������ ����");
                    return;
                }
                else
                {
                    window.open(menuArr[j][2], 'contPop', 'width=600, height=500, scrollbars=yes');
                    isFind = true;
                    break;
                }
            }
        }
        if(isFind) break;
    }
}

function menuLinkPopup(str)

{
                    window.open(str, 'contPopup', 'width=1024, height=768, scrollbars=yes,,titlebar=yes, status=yes,resizable=yes,fullscreen=yes ');


}

