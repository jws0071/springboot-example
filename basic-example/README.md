Spring Boot CRUD Project
=========================
> 스프링부트의 기본 프로젝트 (JPA이용하여 CRUD 해보기)

## 프로젝트 기본 구성

##### Spring Boot / JPA / H2 / Thymeleaf 

##### Controller - Service - ServiceImpl - Repository - Model


## CRUD 생성 

#### Select 부분(전체 호출)

###### Controller
```Java
//사용자 List 화면
@RequestMapping(value = "/list", method=RequestMethod.GET)
public String list(Model model){
    List<BasicModel> lists=basicService.getAllStudyTable();
    System.out.println(lists);
    model.addAttribute("lists", lists);
    return "list";
}
```
###### Service

```Java
List<BasicModel> getAllStudyTable();
```

###### ServiceImpl

```Java
@Override
public List<BasicModel> getAllStudyTable() {
    System.out.println(basicRepository.findAll());
    return basicRepository.findAll();
}
```

#### Select 부분(특정 값만)

###### Controller

```Java
//사용자 update 화면 이동시 특정값 호출
@RequestMapping(value = "/update/{id}",  method={RequestMethod.GET,RequestMethod.POST})
public String update(Model model,@PathVariable String id ){
    System.out.println(id);
    basicService.getStudyTable(Integer.parseInt(id)).ifPresent(o -> model.addAttribute("get_data", o));
    return "update";
}
```
###### Service

```Java
Optional<BasicModel> getStudyTable(Integer id);
```

###### ServiceImpl

```Java
@Override
public Optional<BasicModel> getStudyTable(Integer id) {
    return basicRepository.findById(id);
}

```

###### Repository

```Java
Optional<BasicModel> findById(int id);
```

#### Insert 부분

###### Controller

```Java
//신규 회원 저장
@RequestMapping(value = "/insert_table", method=RequestMethod.POST)
public String insert_table(BasicModel basicModel, @RequestParam(value="name1",required=false)String name, Model model) {
    System.out.println("name : "+ name);
    basicModel.setName(name);
    basicService.insert_table(basicModel);

    List<BasicModel> lists=basicService.getAllStudyTable();
    System.out.println(lists);
    model.addAttribute("lists", lists);
    return "list";
}
```

###### Service

```Java
//테이블 저장
BasicModel insert_table(BasicModel e);
```

###### ServiceImpl

```Java
//테이블 저장
@Override
public BasicModel insert_table(BasicModel e) {
    System.out.println(e);
    return basicRepository.save(e);
}
```


#### Update 부분

###### Controller

```Java
//사용자 update 저장
@RequestMapping(value = "/update_table", method=RequestMethod.POST)
public String update_table(BasicModel basicModel, @RequestParam(value="id1",required=false)String id,@RequestParam(value="name1",required=false)String name, Model model) {
    System.out.println("id : "+ id);
    System.out.println("name : "+ name);
    basicModel.setId(Integer.parseInt(id));
    basicModel.setName(name);
    basicService.update_table(basicModel);
    //System.out.println(studyTable);

    List<BasicModel> lists=basicService.getAllStudyTable();
    System.out.println(lists);
    model.addAttribute("lists", lists);
    return "list";
}
```

###### Service

```Java
//테이블 업데이트
void update_table(BasicModel e);
```

###### ServiceImpl

```Java
@Override
public void update_table(BasicModel e) {
    basicRepository.update(e);
    return ;
}
```

###### Repository

```Java
@Transactional
@Modifying	// update , delete Query시 @Modifying 어노테이션을 추가
@Query(value="update BASIC_MODEL T set T.name = :#{#e.name} WHERE T.id = :#{#e.id}", nativeQuery=true)
Integer update(@Param("e")  BasicModel e );
```


#### Delete 부분

###### Controller

```Java
//사용자 delete 완료
@RequestMapping(value = "/delete_table", method=RequestMethod.POST)
public String delete(BasicModel basicModel, @RequestParam(value="name1",required=false)String name, Model model) {
    System.out.println("name : "+ name);
    basicModel.setName(name);
    basicService.delete_table(basicModel);
    //System.out.println(studyTable);

    List<BasicModel> lists=basicService.getAllStudyTable();
    System.out.println(lists);
    model.addAttribute("lists", lists);
    return "list";

}
```

###### Service

```Java
void delete_table(BasicModel e);
```

###### ServiceImpl

```Java
@Override
public void delete_table(BasicModel e) {
    basicRepository.delete(e);

}
```

###### Repository

```Java
@Transactional
@Modifying	// update , delete Query시 @Modifying 어노테이션을 추가
@Query(value="delete BASIC_MODEL T WHERE T.name = :#{#e.name}", nativeQuery=true)
void delete(@Param("e")  BasicModel e );
```




#### OneToMany ManyToOne 부분

###### Model 

-  Basic =  Board (OneToOne)
-  Board = Comment 
   - Board (OneToMany)
   - Comment (ManyToOne)
   
```Java
@Setter
@Getter
@Entity
public class BasicModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String name;


}

@Getter
@Setter
@Entity
public  class BoardModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bid;

    @Column
    private String bName;


    @OneToOne
    @JoinColumn(name="BasicModel_id")
    private BasicModel basicModel;

    @OneToMany(mappedBy="boardModel")
    private List<CommentModel> commentModels = new ArrayList<CommentModel>();

    public void addBasicModels(CommentModel commentModel) {
        this.commentModels.add(commentModel);
    }


}

@Setter
@Getter
@Entity
public class CommentModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cid;

    @Column
    private String cText;

//    @CreationTimestamp
//    private LocalDateTime cDate;

    @ManyToOne
    @JoinColumn(name="BoardModel_id")
    private  BoardModel boardModel;

    public void setBoardModel(BoardModel boardModel) {
        if(this.boardModel != null)
            this.boardModel.getCommentModels().remove(this);
        this.boardModel = boardModel;
        boardModel.getCommentModels().add(this);

    }
}


```
