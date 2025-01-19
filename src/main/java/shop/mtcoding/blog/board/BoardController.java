package shop.mtcoding.blog.board;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import shop.mtcoding.blog.core.error.ex.Exception401;
import shop.mtcoding.blog.user.User;
import shop.mtcoding.blog.user.UserRepository;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class BoardController {

    private final BoardRepository boardRepository;
    // save할 때 사용
    private final HttpSession session;
    private final BoardService boardService;

    @GetMapping("/")
    public String list(HttpServletRequest request) {
        List<Board> boardList = boardService.게시글목록보기();
        request.setAttribute("models", boardList);
        return "board/list";
    }

    @GetMapping("/board/{id}")
    public String detail(@PathVariable("id") Integer id, HttpServletRequest request) {
//        Board board = boardRepository.findById(id);
//        request.setAttribute("model", board);
        // 불리언으로 게시글의 주인인지 아닌지 판단
        request.setAttribute("isOwner", false);
        User sessionUser = (User) session.getAttribute("sessionUser"); // 누가 로그인했는지
        
        BoardResponse.DetailDTO detailDTO = boardService.상세보기(id, sessionUser);
        request.setAttribute("model", detailDTO);
        return "board/detail";

    }

    @GetMapping("/api/board/save-form")
    public String saveForm() {
        return "board/save-form";
    }

    @GetMapping("/api/board/{id}/update-form")
    public String updateForm(@PathVariable("id") int id, HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser"); // 가져온다,
        Board board = boardService.게시글수정화면가기(id,sessionUser); //보드타입이니까
        request.setAttribute("model", board);
        return "board/update-form";
    }

    @PostMapping("/api/board/save")
    public String save(@Valid BoardRequest.SaveDTO saveDTO, Errors erros) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        boardService.게시글쓰기(saveDTO, sessionUser);
        return "redirect:/";
    }

    @PostMapping("/api/board/{id}/delete") // get으로하면 url에 뜨니까 마음대로 삭제를 할 수 있음 그래서 공개 X
    public String delete(@PathVariable("id") int id) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        boardService.게시글삭제(id, sessionUser);
        return "redirect:/";
    }

    @PostMapping("/api/board/{id}/update")
    public String update(@PathVariable("id") int id, @Valid BoardRequest.UpdateDTO updateDTO, Errors erros) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        boardService.게시글수정(id,updateDTO, sessionUser);
        return "redirect:/" + id;
    }
    //레이지로딩 test
    @GetMapping("/test/board/1")
    public @ResponseBody List<Board> testBoard() {
        //여기까지는 레이지 유저 안 땡겨온다.
        List<Board> boardList = boardRepository.findAll();
        System.out.println("lazy--------------------");
        //보드 리스트를 리턴해버리면 getter 다 때려서 json으로 바꿔야 한다.
        return boardList;
    }
    @GetMapping("/test/board/2")
    public @ResponseBody void testBoard2() {
        //여기까지는 레이지 유저 안 땡겨옴
        List<Board> boardList = boardRepository.findAll();
        System.out.println("---------------------------------");
        //보드 리스트를 리턴해버리면? getter다 때려서 json로 바꿔야 함
        System.out.println(boardList.get(2).getUser().getPassword());
        System.out.println("---------------------------------------------");
    }
}