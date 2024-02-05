function startSlideshow() {
  let currentIdx = 0;
  const pictures = document.querySelectorAll(".main_pic");
  const intervalTime = 5000;
  let slideshowInterval;

  pictures[currentIdx].classList.add("active");

  function nextPic() {
    pictures[currentIdx].classList.remove("active");
    currentIdx = (currentIdx + 1) % pictures.length;
    pictures[currentIdx].classList.add("active");
  }

  slideshowInterval = setInterval(nextPic, intervalTime);

  function toggleBtn() {
    let btn = document.querySelector(".main_toggle_btn");
    let timer = document.querySelector(".timer");

    if (timer.style.animationPlayState == "paused" && !slideshowInterval) {
      timer.style.animationPlayState = "running";
      btn.innerHTML = "||";
      animationPaused = false;
      timer.classList.remove("main_new_timer");
      timer.classList.add("main_timer");
      timer = document.querySelector(".main_timer");
      slideshowInterval = setInterval(nextPic, intervalTime);
    } else {
      timer.style.animationPlayState = "paused";
      btn.innerHTML = "▷";
      animationPaused = true;
      timer.classList.remove("main_timer");
      timer.classList.add("main_new_timer");
      timer = document.querySelector(".main_new_timer");

      clearInterval(slideshowInterval);
      slideshowInterval = null;
    }
  }
  document
    .querySelector(".main_toggle_btn")
    .addEventListener("click", toggleBtn);
}

document.addEventListener("DOMContentLoaded", startSlideshow);
